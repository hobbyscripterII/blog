package com.project.blog.board.vo;

import lombok.Data;

@Data
public class BoardGetVo {
	private int rowNum;
	private int iboard;
	private String icode;
	private String name;
	private String boardName;
	private String title;
	private String userName;
	private String createdAt;
	private String thumbnail;
	private String youtubeId;
	private String url;
	private String secYn;
	private String delYn;
}