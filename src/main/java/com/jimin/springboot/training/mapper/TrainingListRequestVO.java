package com.jimin.springboot.training.mapper;

import lombok.Getter;


/*
 훈련과정 목록을 요청하는 VO

 */
@Getter
public class TrainingListRequestVO {

    //훈련 시작일
    private String srchTraStDt;
    
    //훈련 종료일
    private String srchTraEndDt;

    /**
     * DB에서 가져와야할 정보들
     * 
     */
    private String srchTraOrganNm = "넥스트아이티";
    
    //지역분류
    private int srchTraArea1 = 30;
    private int srchTraArea2 = 30140;

    //NCS직종 분류
    private int srchNcs1 = 20;
    private int srchNcs2 = 2001;
}
