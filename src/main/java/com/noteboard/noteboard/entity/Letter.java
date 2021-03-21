package com.noteboard.noteboard.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Letter {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    Account account;

    String content;
    String fromAccount;
    String toAccount;

    public Letter(String fromAccount, String toAccount, String content, Account account){
        this.fromAccount=fromAccount;
        this.toAccount=toAccount;
        this.content=content;
        this.account=account;
    }

    public Letter(){

    }



}
