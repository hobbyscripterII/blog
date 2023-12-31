package com.my.blog.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInsDto {
    private int categoryId;
    private int boardId;
    private int userId;
    private String title;
    private String contents;
}
