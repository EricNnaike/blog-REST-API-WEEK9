package com.uchenna.restblogweek9.service;

import com.uchenna.restblogweek9.dto.*;
import com.uchenna.restblogweek9.pojo.*;

public interface UserService {
    RegisterResponse register(UserDTO userDTO);
    LoginResponse login(LoginDTO loginDTO);
    PostResponse createPost(PostDTO postDTO);
    CommentResponse comment(int user_id, int post_id, CommentDTO commentDTO);
    LikeResponse liked(int user_id, int post_id, LikeDTO likeDTO);
    SearchCommentResponse searchComment(String keyword);
    SearchPostResponse searchPost(String keyword);
    PostResponse editPost(int post_id, PostDTO postDTO);

}
