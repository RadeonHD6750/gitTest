package com.jimin.springboot.common.mapper;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;


@Getter
@Setter
public class ResponseVO {
    private int statusCode;
    private int loginCode;
    private String message;
    private String redirectUrl;
    private HashMap<String, Object> data;
}
