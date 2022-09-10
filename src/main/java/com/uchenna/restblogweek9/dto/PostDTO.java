package com.uchenna.restblogweek9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
    private String title;
    private String description;
    private String featuredImage;
    private int user_id;
}
