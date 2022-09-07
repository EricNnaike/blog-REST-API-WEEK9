package com.uchenna.restblogweek9.service;

import com.uchenna.restblogweek9.dto.*;
import com.uchenna.restblogweek9.model.Comment;
import com.uchenna.restblogweek9.model.Like;
import com.uchenna.restblogweek9.model.Post;
import com.uchenna.restblogweek9.model.User;
import com.uchenna.restblogweek9.response.*;

public interface UserService {
    RegisterResponse register(UserDTO userDTO);
    LoginResponse login(LoginDTO loginDTO);
    CreatePostResponse createPost(PostDTO postDTO);
    CommentResponse comment(int user_id, int post_id, CommentDTO commentDTO);
    LikeResponse liked(int user_id, int post_id, LikeDTO likeDTO);
    SearchCommentResponse searchComment(String keyword);
    SearchPostResponse searchPost(String keyword);

}
