package com.uchenna.restblogweek9.controller;

import com.uchenna.restblogweek9.dto.CommentDTO;
import com.uchenna.restblogweek9.dto.LikeDTO;
import com.uchenna.restblogweek9.dto.PostDTO;
import com.uchenna.restblogweek9.dto.UserDTO;
import com.uchenna.restblogweek9.pojo.*;
import com.uchenna.restblogweek9.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;;
    }

    @PostMapping(value = "/register")
    public RegisterResponse register(@RequestBody UserDTO userDTO) {
        log.info("Successfully registered {} ", userDTO.getEmail());
        return userService.register(userDTO);
    }

    @PostMapping(value = "/createPost")
    public PostResponse createPost(@RequestBody PostDTO postDTO) {
        log.info("Successfully created a post with title: {} ", postDTO.getTitle());
        return userService.createPost(postDTO);
    }

    @PostMapping(value = "/comment/{user_id}/{post_id}")
    public CommentResponse comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody CommentDTO commentDTO) {
        log.info("Successfully created a comment that reads: {} ", commentDTO.getComment());
        return userService.comment(user_id, post_id, commentDTO);
    }

    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody LikeDTO likeDTO) {
        log.info("Successfully like post : {} ", likeDTO.isLiked());
        return new ResponseEntity<>(userService.liked(user_id, post_id, likeDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(value = "keyword") String keyword) {
        log.info("Successfully found post with keyword : {} ", keyword);
        return new ResponseEntity<>(userService.searchPost(keyword), HttpStatus.OK);
//        return ResponseEntity.ok().body(userService.searchPost(keyword));
    }

//    @GetMapping("/getPostForm/{post_id}")
//    public PostResponse getPostForm(@PathVariable(value = "post_id") Integer post_id, ) {
//        return
//    }

    @PostMapping (value = "/edit/{post_id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable(value = "post_id") Integer post_id, @RequestBody PostDTO postDTO) {
        log.info("Successfully edited post id : {} ", post_id);
        return new ResponseEntity<>(userService.editPost(post_id, postDTO), HttpStatus.ACCEPTED);
    }

//    @PutMapping(value = "/{postId}",produces = "application/json")
//    public ResponseEntity<APIResponse> updatePost(@RequestBody PostDto postDto, @PathVariable int postId) throws PostNotFoundException {
//        return new ResponseEntity<>(new APIResponse("Post Updated",true,postService.updatePost(postDto,postId)), HttpStatus.CREATED);
//    }



}
