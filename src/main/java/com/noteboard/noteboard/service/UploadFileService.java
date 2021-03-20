package com.noteboard.noteboard.service;


import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.exception.FileDownloadException;
import com.noteboard.noteboard.exception.FileUploadException;
import com.noteboard.noteboard.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadFileService {

    private final Path fileStorageLocation;

    @Autowired
    public UploadFileService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch(Exception ex){
            throw new FileUploadException("Could not create directory");
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(),targetLocation,StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch(Exception ex){
            throw new FileUploadException("Could not store file");
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else{
                throw new FileDownloadException("File not found");
            }
        }catch(Exception ex){
            throw new FileDownloadException("File not found");
        }
    }










}
