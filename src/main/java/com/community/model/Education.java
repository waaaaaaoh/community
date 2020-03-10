package com.community.model;

import lombok.Data;

@Data
public class Education {
    private Long id;
    private String title;
    private String coverImg;
    private String content;
    private Long gmtCreate;
    private Integer viewCount;
    private Integer type;
}
