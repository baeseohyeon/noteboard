package com.noteboard.noteboard.controller;


import com.noteboard.noteboard.dto.AccountDTO;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.repository.AccountRepository;
import com.noteboard.noteboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/user/signup")
    public String dispSignup(){
        return "/signup";
    }


    @PostMapping("/user/signup")
    public String execSignup(Account account){
        accountService.join(account);
        return "redirect:/user/login";

    }

    @GetMapping("/user/login")
    public String dispLogin(){
        return "/login";
    }

    @GetMapping("/user/login/result")
    public String dispLoginResult(){
        return "/loginSuccess";
    }

    @GetMapping("/user/logout/result")
    public String dispLogout(){
        return "/logout";
    }

    @GetMapping("/user/denied")
    public String dispDenied(){
        return "/denied";
    }

    @GetMapping("/user/info")
    public String dispMyInfo(){
        return "/myinfo";
    }

    @GetMapping("/admin")
    public String dispAdmin(){
        return "/admin";
    }


}
