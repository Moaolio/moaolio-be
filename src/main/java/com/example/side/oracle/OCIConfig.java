package com.example.side.oracle;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Configuration
public class OCIConfig {
    @Bean
    public ObjectStorage objectStorageClient() throws IOException {
        String configPath = System.getProperty("user.home") + File.separator + ".oci" + File.separator + "config";        //우분투일경우

        ConfigFileReader.ConfigFile config = ConfigFileReader.parse(configPath, "DEFAULT");

        // 개인 키 파일 경로 확인
        String keyFilePath = config.get("key_file");
        if (keyFilePath == null || keyFilePath.isEmpty()) {
            throw new IllegalStateException("key_file is not specified in the config file");
        }

        // 파일 존재 여부 확인
        if (!new File(configPath).exists()) {
            throw new IllegalStateException("Config file not found: " + configPath);
        }
        if (!new File(keyFilePath).exists()) {
            throw new IllegalStateException("Private key file not found: " + keyFilePath);
        }

        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);
        return new ObjectStorageClient(provider);
    }
}