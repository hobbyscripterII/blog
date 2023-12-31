package com.my.blog.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardVo {
    @Getter
    public static class Get {
        private int boardId;
        private String subjectName;
        private String title;
        private String nm;
        private String createdAt;
    }

    @Getter
    @Setter
    @ToString
    public static class Sel {
        private int categoryId;
        private int boardId;
        private int userId;
        private int subjectId;
        private String subjectName;
        private String boardCategoryName;
        private String title;
        private String contents;
        private String writer;
        private String createdAt;
        private String updatedAt;
    }

    @Getter
    public static class Subject {
        private String subjectId;
        private String subjectName;
    }
}
