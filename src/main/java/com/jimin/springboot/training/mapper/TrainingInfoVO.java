package com.jimin.springboot.training.mapper;

import lombok.Getter;
import lombok.Setter;


/*

 */

@Getter
@Setter
public class TrainingInfoVO {
	/** 주소 */
	private String address;


	/** 컨텐츠 */
	private String contents;


	/** 수강비 */
	private String courseMan;


	/** 고용보험3개월 취업인원 수 */
	private String eiEmplCnt3;


	/** 고용보험3개월 취업누적인원 10인이하 여부(Y/N) */
	private String eiEmplCnt3Gt10;


	/** 고용보험3개월 취업률 */
	private String eiEmplRate3;


	/** 고용보험6개월 취업률 */
	private String eiEmplRate6;


	/** 등급 */
	private String grade;


	/** 훈련기관 코드 */
	private String instCd;


	/** NCS코드 */
	private String ncsCd;


	/** 실제 훈련비 */
	private String realMan;


	/** 수강신청 인원 */
	private String regCourseMan;


	/** 부 제목 */
	private String subTitle;


	/** 부 제목 링크 */
	private String subTitleLink;


	/** 전화번호 */
	private String telNo;


	/** 제목 */
	private String title;


	/** 제목 아이콘 */
	private String titleIcon;


	/** 제목 링크 */
	private String titleLink;


	/** 훈련종료일자 */
	private String traEndDate;


	/** 훈련시작일자 */
	private String traStartDate;


	/** 훈련대상 */
	private String trainTarget;


	/** 훈련구분 */
	private String trainTargetCd;


	/** 훈련기관 ID */
	private String trainstCstId;


	/** 훈련과정 순차 */
	private String trprDegr;


	/** 훈련과정 ID */
	private String trprId;

	/** 정원 */
	private String yardMan;


	private String trngAreaCd;

	/**
	 * 모집상태 코드
	0 - 진행중
	 1 - 모집중
	2 - 종료
	 **/
	private int stateCode;
}
