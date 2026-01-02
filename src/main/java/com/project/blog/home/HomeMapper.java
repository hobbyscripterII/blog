package com.project.blog.home;

import org.apache.ibatis.annotations.Mapper;
import com.project.blog.home.vo.ContentsCntGetVo;

@Mapper
public interface HomeMapper {
	ContentsCntGetVo getContentsCnt();
}
