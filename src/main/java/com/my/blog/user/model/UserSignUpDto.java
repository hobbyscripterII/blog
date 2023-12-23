package com.my.blog.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {
    private String uid;
    private String upw;
    private String unm;
}