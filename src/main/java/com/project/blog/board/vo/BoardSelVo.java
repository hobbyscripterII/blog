package com.project.blog.board.vo;

import lombok.Data;

@Data
public class BoardSelVo {
	private int iboard;
	private String icode;
	private String title;
	private String contents;
	private String youtubeId;
	private String createdAt;
	private String thumbnail;
	private String secYn;
}