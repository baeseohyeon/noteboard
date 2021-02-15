package com.noteboard.noteboard.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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


    private static Post createPost(Account account, String title, String content, String writer){
        Post post = new Post();
        post.setAccount(account);
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        return post;
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
