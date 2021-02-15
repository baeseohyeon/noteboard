package com.noteboard.noteboard.controller;


import com.noteboard.noteboard.adapter.AccountAdapter;
import com.noteboard.noteboard.dto.PostDTO;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String list(Model model){
        List<Post> postList = postService.findPosts();

        List<PostDTO> result = postList.stream().map(p -> new PostDTO(p))
                .collect(Collectors.toList());

        model.addAttribute("postList",result);

        return "board/list.html";
    }

    @GetMapping("/post/write")
    public String write(@AuthenticationPrincipal AccountAdapter accountAdapter){

        Account account = accountAdapter.getAccount();
        System.out.println(account);

        if(account==null){
            return "writefail.html";
        }else {
            return "board/write.html";
        }
    }
    @PostMapping("/post/write")
    public String write( Post post, @AuthenticationPrincipal AccountAdapter accountAdapter){
        System.out.println("stop");
        Account account = accountAdapter.getAccount();
        post.setAccount(account);
        postService.savePost(post);
        return "redirect:/post";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        Post post = postService.findOne(id);
        PostDTO postDto = new PostDTO(post);
        model.addAttribute("postDto",postDto);
        return "board/detail.html";

    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Post post = postService.findOne(id);
        PostDTO postDtO = new PostDTO(post);
        model.addAttribute("postDto", postDtO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{id}")
    public String update(@PathVariable("id") Long id,PostDTO postDTO){

        Post post=postService.findOne(id);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setWriter(postDTO.getWriter());
        postService.updatePost(post);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        postService.deletePost(id);
        return "redirect:/";
    }
}
