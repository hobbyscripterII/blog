package com.jy.myblog.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class AdminUpdDto {
    private String publicFl;
    private int isubject;
    private List<Integer> list;
}
