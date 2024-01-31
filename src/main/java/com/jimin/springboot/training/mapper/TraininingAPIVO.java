package com.jimin.springboot.training.mapper;

import lombok.Getter;

import java.util.List;

/*
 훈련과정 목록 VO
 */

@Getter
public class TraininingAPIVO {
	

	private String pageNum;

	private String pageSize;

	private String scn_cnt;

	//여기다 그 목록 담음
	private List<TrainingInfoVO> srchList;
}
