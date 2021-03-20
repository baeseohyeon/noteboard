package com.noteboard.noteboard.api;


import com.noteboard.noteboard.dto.PostDTO;
import com.noteboard.noteboard.dto.UploadFileDTO;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.entity.UploadFile;
import com.noteboard.noteboard.repository.UploadFileRepository;
import com.noteboard.noteboard.service.AccountService;
import com.noteboard.noteboard.service.PostService;
import com.noteboard.noteboard.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;
    private final EntityManager em;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;

    @PostMapping("/api/post/write")
    public PostDTO writePost(@RequestBody Post post){
        postService.savePost(post);
        return new PostDTO(post);

    }

    @PostMapping("/api/post/{id}/uploadfile")
    public UploadFileDTO uplaodfile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file){

        String fileName = uploadFileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/post")
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(fileName);
        uploadFile.setFileDownloadUri(fileDownloadUri);
        uploadFile.setFileType(file.getContentType());
        uploadFile.setSize(file.getSize());
        postService.addFile(id,uploadFile);
        return new UploadFileDTO(uploadFile);
    }

    @PostMapping("/api/post/{id}/uploadfiles")
    public List<UploadFileDTO> uploadfiles(@PathVariable("id") Long id, @RequestParam("files")MultipartFile[] files){

        // 파일 업로드(여러개) 처리 부분
        List<UploadFile> uploadfiles = new ArrayList<>();
        for(MultipartFile file : files) {
           String fileName = uploadFileService.storeFile(file);
            UploadFile uploadFile = new UploadFile();
            uploadFile.setFileName(fileName);
            uploadFile.setFileType(file.getContentType());
            uploadFile.setSize(file.getSize());
            uploadfiles.add(uploadFile);
        }
        postService.addFiles(uploadfiles,id);
        List<UploadFileDTO> result = uploadfiles.stream().map(file -> new UploadFileDTO(file)).collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v1/files")
    public List<UploadFileDTO> filesv1(){
        List<UploadFile> files = uploadFileRepository.findAll();
        List<UploadFileDTO> list = files.stream().map(file -> new UploadFileDTO(file)).collect(Collectors.toList());
        return list;
    }


    @GetMapping("/api/v1/posts")
    public List<Post> postsv1(){
        List<Post> posts = postService.findPosts();
        return posts;
    }

    @GetMapping("/api/v2/posts")
    public List<PostDTO> postsv2(){
        List<Post> posts = postService.findPosts();
        List<PostDTO> result = posts.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v3/posts")
    public List<PostDTO> postsv3(){
        List<Post> resultList = em.createQuery("select p from Post p" +
                " join fetch p.account a", Post.class).getResultList();
        List<PostDTO> collect = resultList.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
        return collect;
    }




}
