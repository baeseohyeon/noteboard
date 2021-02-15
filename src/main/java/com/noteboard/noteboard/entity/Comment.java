package com.noteboard.noteboard.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter
public class Comment {



    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String title;
    private String content;

    private String writer;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(title, comment.title) && Objects.equals(content, comment.content) && Objects.equals(writer, comment.writer) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, writer, post);
    }
}
