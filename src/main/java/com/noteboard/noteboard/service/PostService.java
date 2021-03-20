package com.noteboard.noteboard.service;


import com.noteboard.noteboard.dto.PostDTO;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.entity.UploadFile;
import com.noteboard.noteboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수


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
        post.setTitle(post.getTitle());
        post.setContent(post.getContent());
        post.setWriter(post.getWriter());

    }

    public void deletePost(Long postId){

        postRepository.deleteById(postId);

    }

    public Long getPostCount() {
        return postRepository.count();
    }

    public List<PostDTO> searchPostsByKeyword(String keyword) {

        List<Post> postList=postRepository.findByTitleContaining(keyword);

        List<PostDTO> postDTOList = postList.stream().map((post) -> new PostDTO(post)).collect(Collectors.toList());

        return postDTOList;

    }
    public List<PostDTO> searchPosts(Integer pageNum) {

        Page<Post> page = postRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<Post> postList= page.getContent();

        List<PostDTO> postDTOList = postList.stream().map((post) -> new PostDTO(post)).collect(Collectors.toList());

        return postDTOList;

    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getPostCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                 ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    public void addPost(Account account, Post post) {
        post.setAccount(account);
        postRepository.save(post);
    }

    public void addFile(Long postId,UploadFile file){
        Post post = postRepository.findById(postId).get();
        file.setPost(post);
        postRepository.save(post);
    }
    public void addFiles(List<UploadFile> files,Long postId){
        Post post = postRepository.findById(postId).get();
        files.forEach(file -> file.setPost(post));
        postRepository.save(post);
    }

}
