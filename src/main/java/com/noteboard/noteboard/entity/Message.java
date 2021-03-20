package com.noteboard.noteboard.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Message extends TimeEntity{

    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long id;


    @JoinColumn(name="account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private String from;
    private String to;
    private String content;


    public Message(String from, String to, String content){
        this.from=from;
        this.to=to;
        this.content=content;
    }


    public Message() {

    }
}
