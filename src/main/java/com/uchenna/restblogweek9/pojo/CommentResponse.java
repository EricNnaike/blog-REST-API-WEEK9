package com.uchenna.restblogweek9.pojo;

import com.uchenna.restblogweek9.model.Comment;
import com.uchenna.restblogweek9.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CommentResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private Post post;
}
