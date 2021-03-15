package com.noteboard.noteboard.entity;


import lombok.Getter;
import lombok.Setter;

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


    private static Post createPost(Account account, String title, String content, String writer){
        Post post = new Post();
        post.setAccount(account);
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        return post;
    }

    public void addAccount(Account account){
        this.setAccount(account);
        account.getPosts().add(this);
    }


    public void addFile(UploadFile uploadFile){
        uploadFiles.add(uploadFile);
        uploadFile.setPost(this);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && title.equals(post.title) && content.equals(post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }
}
