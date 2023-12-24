package com.my.blog.user;

import com.my.blog.user.model.UserEntity;
import com.my.blog.user.model.UserSignInDto;
import com.my.blog.user.model.UserSignUpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int signUp(UserSignUpDto dto);
    UserEntity signIn(String uid);
}