package com.my.blog.board.model;

import lombok.Getter;

public class BoardVo {
    @Getter
    public static class Get {
        private int boardId;
        private String title;
        private String nm;
        private String createdAt;
    }

    @Getter
    public static class Sel {
        private String boardCategoryName;
        private String title;
        private String contents;
        private String writer;
        private String createdAt;
        private String updatedAt;
    }
}
