package com.uchenna.restblogweek9.pojo;

import com.uchenna.restblogweek9.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Post post;
}
