package com.example.side.oracle;

import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.DeleteObjectRequest;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ObjectStorageService {

    private ObjectStorage client;
    private UploadManager uploadManager;
    private final String namespaceName = "cnshljz7n2kf";
    private final String bucketName = "bucket-20240928-2302";

    @PostConstruct
    public void init() throws IOException {
        // OCI 설정 파일에서 인증 정보를 로드합니다.
        String configurationFilePath = System.getProperty("user.home") + "/.oci/config";
        String profile = "DEFAULT";
        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        // ObjectStorage 클라이언트를 생성합니다.
        this.client = ObjectStorageClient.builder().build(provider);

        // UploadManager를 설정합니다.
        UploadConfiguration uploadConfiguration = UploadConfiguration.builder()
                .allowMultipartUploads(true)
                .allowParallelUploads(true)
                .build();
        this.uploadManager = new UploadManager(client, uploadConfiguration);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String objectName = generateUniqueFileName(file.getOriginalFilename());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .namespaceName(namespaceName)
                .bucketName(bucketName)
                .objectName(objectName)
                .contentType(file.getContentType())
                .build();

        UploadManager.UploadRequest uploadRequest = UploadManager.UploadRequest.builder(file.getInputStream(), file.getSize())
                .allowOverwrite(true)
                .build(putObjectRequest);

        uploadManager.upload(uploadRequest);

        return objectName;
    }

    public void deleteImage(String objectName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .namespaceName(namespaceName)
                .bucketName(bucketName)
                .objectName(objectName)
                .build();

        client.deleteObject(deleteObjectRequest);
    }

    public byte[] getImage(String objectName) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .namespaceName(namespaceName)
                .bucketName(bucketName)
                .objectName(objectName)
                .build();

        GetObjectResponse response = client.getObject(getObjectRequest);

        try (InputStream inputStream = response.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String randomUUID = UUID.randomUUID().toString().substring(0, 8);
        String fileExtension = getFileExtension(originalFilename);
        return timeStamp + "_" + randomUUID + fileExtension;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

}