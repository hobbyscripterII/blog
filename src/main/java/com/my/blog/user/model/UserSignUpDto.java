package com.my.blog.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignUpDto {
    private String uid;
    private String upw;
    private String unm;
}