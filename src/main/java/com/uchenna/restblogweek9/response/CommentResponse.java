package com.uchenna.restblogweek9.response;

import com.uchenna.restblogweek9.model.Comment;
import com.uchenna.restblogweek9.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CommentResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Comment comment;
}
