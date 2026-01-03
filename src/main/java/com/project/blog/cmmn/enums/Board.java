package com.project.blog.cmmn.enums;

import lombok.Getter;

@Getter
public enum Board {
	MUSIC ("음악 게시판", "B004"),
	PHOTO ("사진 게시판", "B003"),
	REVIEW("리뷰 게시판", "B008"),
	DAILY ("일상 게시판", "B005");

	public final String BOARD_NAME;
	public final String ICODE;

	Board(String boardName, String icode) {
		this.BOARD_NAME = boardName;
		this.ICODE      = icode;
	}
}