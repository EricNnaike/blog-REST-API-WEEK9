package com.uchenna.restblogweek9.response;

import com.uchenna.restblogweek9.model.Post;
import com.uchenna.restblogweek9.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class SearchPostResponse {
    private String message;
    private LocalDateTime timeStamp;
    private List<Post> posts;
}
