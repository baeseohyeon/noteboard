package com.noteboard.noteboard.controller;


import com.noteboard.noteboard.adapter.AccountAdapter;
import com.noteboard.noteboard.dto.PostDTO;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.entity.UploadFile;
import com.noteboard.noteboard.repository.UploadFileRepository;
import com.noteboard.noteboard.service.PostService;
import com.noteboard.noteboard.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;

    @GetMapping("/post")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1")Integer pageNum){


        List<PostDTO> postList = postService.searchPosts(pageNum);
        Integer[] pageList = postService.getPageList(pageNum);


        model.addAttribute("postList",postList);
        model.addAttribute("pageList", pageList);
        return "board/list.html";
    }

    @GetMapping("/post/write")
    public String write(){
            return "board/write.html";
    }

    @PostMapping("/post/write")
    public String write(Post post, @AuthenticationPrincipal AccountAdapter accountAdapter, @RequestParam("file")MultipartFile file){
        System.out.println("start");
        String fileName = uploadFileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/post")
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        Account account = accountAdapter.getAccount();
        post.addAccount(account);
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(fileName);
        uploadFile.setFileDownloadUri(fileDownloadUri);
        uploadFile.setFileType(file.getContentType());
        uploadFile.setSize(file.getSize());
        post.addFile(uploadFile);
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
    public String edit(@PathVariable("id") Long id, Model model , @AuthenticationPrincipal AccountAdapter accountAdapter) {
        Account account = accountAdapter.getAccount();
        Post post = postService.findOne(id);
        Account postAccount = post.getAccount();
        System.out.println(postAccount);
        System.out.println(account);
        if (account.getId() == postAccount.getId()) {
            PostDTO postDtO = new PostDTO(post);
            model.addAttribute("postDto", postDtO);
            return "board/update.html";
        }else{
            return "redirect:/post";
        }

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

    @GetMapping("/post/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model){
        List<PostDTO> postDTOList = postService.searchPostsByKeyword(keyword);
        model.addAttribute("postList",postDTOList);

        return "board/list.html";
    }


}
