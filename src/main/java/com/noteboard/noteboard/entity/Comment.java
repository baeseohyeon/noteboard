package com.noteboard.noteboard.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Comment {


    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private String writer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPsot(Post post){
        this.post=post;
        post.getComments().add(this);
    }

}
