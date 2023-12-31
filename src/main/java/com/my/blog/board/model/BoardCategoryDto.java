package com.my.blog.board.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardCategoryDto {
    private int categoryId;
    private String boardName;
}