package com.noteboard.noteboard.service;


import com.noteboard.noteboard.dto.PostDTO;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;


    public void savePost(Post post){
        postRepository.save(post);
    }

    public Post findOne(Long postId){
        return postRepository.findById(postId).get();
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public void updatePost(Post post){
        postRepository.save(post);

    }

    public void deletePost(Long postId){

        postRepository.deleteById(postId);

    }


}
