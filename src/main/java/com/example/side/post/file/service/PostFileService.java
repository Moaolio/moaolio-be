package com.example.side.post.file.service;

import com.example.side.post.file.entity.PostFile;
import com.example.side.post.file.repository.PostFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostFileService {
    @Autowired
    private PostFileRepository postFileRepository;

    public PostFile saveFile(MultipartFile file, String fileUrl) {
        PostFile postFile = new PostFile();
        postFile.setFileName(file.getOriginalFilename());
        postFile.setFileUrl(fileUrl);
        postFile.setFileType(file.getContentType());
        postFile.setFileSize(file.getSize());
        postFile.setFileOriginName(file.getOriginalFilename());
        return postFileRepository.save(postFile);
    }


}
