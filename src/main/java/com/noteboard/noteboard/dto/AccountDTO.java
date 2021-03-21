package com.noteboard.noteboard.dto;

import com.noteboard.noteboard.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AccountDTO {



    private Long id;
    private String username;
    private String Email;
    private List<PostDTO> postDTOList;
    private List<CommentDTO> commentDTOList;

    public AccountDTO(Account account){
        id=account.getId();
        username=account.getUsername();
        Email=account.getEmail();
    }
}
