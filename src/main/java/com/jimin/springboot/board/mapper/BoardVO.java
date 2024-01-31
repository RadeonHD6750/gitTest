package com.jimin.springboot.board.mapper;

import lombok.Getter;
import lombok.Setter;
import oracle.sql.DATE;

import java.sql.Date;

@Getter
@Setter
public class BoardVO {
    private Long contentId;
    private String userId;
    private String title;
    private String contents;
    private String categoryId;
    private Date createdDate;
    private Date updatedDate;
    private int enabled;
}
