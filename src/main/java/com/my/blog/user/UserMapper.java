package com.my.blog.user;

import com.my.blog.user.model.UserSignUpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int signUp(UserSignUpDto dto);
}