package com.uchenna.restblogweek9.dto;

import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String description;
    private String featuredImage;
    private int user_id;
}
