package com.community.model;

import lombok.Data;

@Data
public class Show {
    private Long id;
    private String title;
    private Long gmtCreate;
    private Integer viewCount;
    private String content;
    private Integer type;
}
