package com.jimin.springboot.training.controller;


import com.jimin.springboot.security.service.SecurityService;
import com.jimin.springboot.training.mapper.TrainingInfoVO;
import com.jimin.springboot.training.mapper.TrainingListRequestVO;
import com.jimin.springboot.training.service.TrainingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TrainingController {

    private final SecurityService securityService;
    private final TrainingService trainingService;

    @Autowired
    public TrainingController(SecurityService securityService, TrainingService trainingService) {
        this.securityService = securityService;
        this.trainingService = trainingService;
    }


    @GetMapping("/training/list")
    public String list(HttpServletRequest request) throws Exception{
        System.out.println("/training/list");
        return securityService.forwardLoginProtect(request, "training/hrd-net");
    }



    //지정된 날짜를 기준으로 걍 싹다 훈련과정 조회하기
    @ResponseBody
    @RequestMapping(value = "/SELECT_TRAINING_ALL_LIST", method = RequestMethod.POST)
    public List<TrainingInfoVO> SELECT_TRAINING_API_LIST(@RequestBody TrainingListRequestVO trainingListRequestVO) throws Exception
    {
        List<TrainingInfoVO> trainingListAPIVOList = trainingService.SELECT_TRAINING_ALL_LIST(trainingListRequestVO);

        return trainingListAPIVOList;
    }

    //현재날짜를 기준으로 진행중인 훈련과정 조회하기
    @ResponseBody
    @RequestMapping(value = "/SELECT_TRAINING_DOING_LIST", method = RequestMethod.POST)
    public List<TrainingInfoVO> SELECT_TRAINING_DOING_LIST(@RequestBody TrainingListRequestVO trainingListRequestVO) throws Exception
    {
        List<TrainingInfoVO> trainingListAPIVOList = trainingService.SELECT_TRAINING_DOING_LIST(trainingListRequestVO);

        return trainingListAPIVOList;
    }

    //현재날짜를 기준으로 모집중인 훈련과정 조회하기
    @ResponseBody
    @RequestMapping(value = "/SELECT_TRAINING_RECRUIT_LIST", method = RequestMethod.POST)
    public List<TrainingInfoVO> SELECT_TRAINING_RECRUIT_LIST(@RequestBody TrainingListRequestVO trainingListRequestVO) throws Exception
    {
        List<TrainingInfoVO> trainingListAPIVOList = trainingService.SELECT_TRAINING_RECRUIT_LIST(trainingListRequestVO);

        return trainingListAPIVOList;
    }


    //현재날짜를 기준으로 종료된 훈련과정 조회하기
    @ResponseBody
    @RequestMapping(value = "/SELECT_TRAINING_DONE_LIST", method = RequestMethod.POST)
    public List<TrainingInfoVO> SELECT_TRAINING_DONE_LIST(@RequestBody TrainingListRequestVO trainingListRequestVO) throws Exception
    {
        List<TrainingInfoVO> trainingListAPIVOList = trainingService.SELECT_TRAINING_DONE_LIST(trainingListRequestVO);

        return trainingListAPIVOList;
    }
}
