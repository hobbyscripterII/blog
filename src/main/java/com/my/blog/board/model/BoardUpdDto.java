package com.my.blog.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardUpdDto {
    private int boardId;
    private String title;
    private String contents;
}
