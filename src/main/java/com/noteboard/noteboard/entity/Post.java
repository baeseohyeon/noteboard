package com.noteboard.noteboard.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter @Setter
public class Post extends TimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<UploadFile> uploadFiles=new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public void addFile(UploadFile uploadFile){
        uploadFiles.add(uploadFile);
        uploadFile.setPost(this);
    }


}
