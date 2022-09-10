package com.uchenna.restblogweek9.pojo;

import com.uchenna.restblogweek9.model.Like;
import com.uchenna.restblogweek9.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class LikeResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Like like;
    private int totalLikes;
    private Post post;
}
