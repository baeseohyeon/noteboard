package com.noteboard.noteboard.dto;

import com.noteboard.noteboard.entity.UploadFile;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UploadFileDTO {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileDTO(UploadFile file){
        fileName=file.getFileName();
        fileDownloadUri=file.getFileDownloadUri();
        fileType=file.getFileType();
        size=file.getSize();
    }


}
