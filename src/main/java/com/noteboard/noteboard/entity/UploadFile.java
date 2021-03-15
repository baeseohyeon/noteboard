package com.noteboard.noteboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class UploadFile {

    @Id @GeneratedValue
    @Column(name = "upload_file_id")
    private Long id;

    @Column
    private String fileName;

    @Column
    private String fileDownloadUri;

    @Column
    private String fileType;

    @Column
    private Long size;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
