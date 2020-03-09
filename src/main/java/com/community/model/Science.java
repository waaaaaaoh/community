package com.community.model;

import lombok.Data;

@Data
public class Science {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Long gmtCreate;
    private Integer viewCount;
    private Integer type;
}
