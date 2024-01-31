package com.jimin.springboot.training.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimin.springboot.training.mapper.TrainingInfoVO;
import com.jimin.springboot.training.mapper.TrainingListRequestVO;
import com.jimin.springboot.training.mapper.TraininingAPIVO;
import com.jimin.springboot.util.JiminUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class TrainingService {

	//API KEY
	@Value("${hrd-net.api.dev}")
	String authKey;

	//요청 URL
	@Value("${hrd-net.url.main}")
	String base_url;

	//요청 URL
	@Value("${hrd-net.url.api}")
	String api_url;


	/******************************************
	 * 뭔가 DB에서 가져와야할 듯한 정보들
	 * 학원정보임으로 DB에서 가져와야할 것으로 보임
	 *****************************************/


	private final String org_name = "넥스트아이티";


	//이사가지 않는 이상 고정
	//훈련지역 대분류
	private final int srchTraArea1 = 30;

	//훈련지역 소분류
	private final int srchTraArea2 = 30140;


	//NCS직종 대분류
	private final int srchNcs1 = 20;


	//NCS직종 중분류
	private final int srchNcs2 = 2001;


	public List<TrainingInfoVO> SELECT_TRAINING_ALL_LIST(TrainingListRequestVO trainingListRequestVO) throws Exception
	{
		List<TrainingInfoVO> trainingListAPIVOList = requestTrainingInfoAPI(trainingListRequestVO);

		List<TrainingInfoVO> trainingAllList = filterTrainingState(trainingListAPIVOList, 3);
		return trainingAllList;
	}


	public List<TrainingInfoVO> SELECT_TRAINING_DOING_LIST(TrainingListRequestVO trainingListRequestVO) throws Exception
	{
		List<TrainingInfoVO> trainingListAPIVOList = requestTrainingInfoAPI(trainingListRequestVO);

		List<TrainingInfoVO> trainingDoneList = filterTrainingState(trainingListAPIVOList, 0);

		return trainingDoneList;
	}


	public List<TrainingInfoVO> SELECT_TRAINING_RECRUIT_LIST(TrainingListRequestVO trainingListRequestVO) throws Exception
	{
		List<TrainingInfoVO> trainingListAPIVOList = requestTrainingInfoAPI(trainingListRequestVO);

		List<TrainingInfoVO> trainingRecruitList = filterTrainingState(trainingListAPIVOList, 1);

		return trainingRecruitList;
	}

	public List<TrainingInfoVO> SELECT_TRAINING_DONE_LIST(TrainingListRequestVO trainingListRequestVO) throws Exception
	{
		List<TrainingInfoVO> trainingListAPIVOList = requestTrainingInfoAPI(trainingListRequestVO);

		List<TrainingInfoVO> trainingDoneList = filterTrainingState(trainingListAPIVOList, 2);


		return trainingDoneList;
	}



	public List<TrainingInfoVO> requestTrainingInfoAPI(TrainingListRequestVO trainingListRequestVO) throws Exception {


		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(base_url);
		stringBuilder.append(api_url);

		stringBuilder.append("pageNum=1");

		//훈련기관
		stringBuilder.append("&srchTraStDt=");
		stringBuilder.append(trainingListRequestVO.getSrchTraStDt());
		stringBuilder.append("&srchTraEndDt=");
		stringBuilder.append(trainingListRequestVO.getSrchTraEndDt());

		stringBuilder.append("&sort=ASC");
		stringBuilder.append("&pageSize=20");

		//훈련기관명
		stringBuilder.append("&srchTraOrganNm=");
		stringBuilder.append(org_name);

		//지역코드
		stringBuilder.append("&srchTraArea1=");
		stringBuilder.append(srchTraArea1);
		stringBuilder.append("&srchTraArea2=");
		stringBuilder.append(srchTraArea2);

		stringBuilder.append("&outType=1");

		//NCS 직종
		stringBuilder.append("&srchNcs1=");
		stringBuilder.append(srchNcs1);
		stringBuilder.append("&srchNcs2=");
		stringBuilder.append(srchNcs2);

		//인증키
		stringBuilder.append("&authKey=");
		stringBuilder.append(authKey);

		stringBuilder.append("&returnType=JSON");
		stringBuilder.append("&sortCol=TRNG_BGDE");

		String request_url = stringBuilder.toString();

		System.out.println("SELECT_TRAINING_API_LIST URL");
		System.out.println(request_url);

		List<TrainingInfoVO> resultList = null;

		try {

			String raw = JiminUtil.callGetforJSON(request_url);

			System.out.println("received raw String");
			//System.out.println(raw);

			TraininingAPIVO traininingAPIVO = getTrainingManageApiVOFromString(raw);



			resultList = traininingAPIVO.getSrchList();


			System.out.println("SELECT_TRAINING_API_LIST RESULT");
			System.out.println("data size = " +  resultList.size());


		} catch (Exception e) {
			e.printStackTrace();

		}


		return resultList;
	}

	/******************
	 * 
	 * 각종 지원함수
	 *
	 *****************/

	//날짜를 기준으로 종료된 것인지
	//모집중인 것인지 구분하여 statecode 부여
	public List<TrainingInfoVO> filterTrainingState(List<TrainingInfoVO> dataList, int mode)
	{
		List<TrainingInfoVO> trainingFilteredList = new LinkedList<>();


		for (TrainingInfoVO data: dataList) {

			String traStartDate = data.getTraStartDate();
			String traEndDate = data.getTraEndDate();

			int stateCode = JiminUtil.compareDate(traStartDate, traEndDate);

			//VO라서 신규 생성 후 넣어야 하는 것이 맞으나
			//그럼 너무 코드가 길어지니 stateCode만 값을 변경
			data.setStateCode(stateCode);

			if(stateCode == mode || mode == 3)
			{
				trainingFilteredList.add(data);
			}

		}

		return trainingFilteredList;
	}

	//이상하게 날아오는 JSON String을 원하는 VO로 변환해주는 특수함수
	public TraininingAPIVO getTrainingManageApiVOFromString(String raw) {

		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, Object> removedSlashMap = null;
		HashMap<String, Object> removedRootMap = null;

		TraininingAPIVO result = null;

		try
		{
			removedSlashMap = mapper.readValue(raw, HashMap.class);
		}
		catch (JsonProcessingException e)
		{
			System.out.println(e.getMessage());
			System.out.println("removedSlashMap = mapper.readValue(removed_slash, Map.class);");
		}


		if(removedSlashMap != null && !removedSlashMap.isEmpty())
		{
			String srchList_json = (String) removedSlashMap.get("returnJSON");

			try
			{
				removedRootMap = mapper.readValue(srchList_json, HashMap.class);
			}
			catch (JsonProcessingException e)
			{
				System.out.println(e.getMessage());
				System.out.println("removedRootMap = mapper.readValue(srchList_json, HashMap.class);");
			}
		}


		if(removedRootMap != null && !removedRootMap.isEmpty())
		{
			result = mapper.convertValue(removedRootMap, TraininingAPIVO.class);
		}


		return result;
	}
}
