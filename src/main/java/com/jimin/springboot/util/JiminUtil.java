package com.jimin.springboot.util;

import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JiminUtil {

    //고정 포맷
    static SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");



    //GET 통신하기
    public static String callGetforJSON(String request_url) throws Exception
    {
        System.out.println("callGetforJSON");
        RestTemplate template = new RestTemplate();

        // API 호출
        String response = template.getForObject(request_url, String.class);

        return response;
    }



    //현재 날짜 구하기
    public static Date getNowDate()
    {
        Date date = new Date();

        return date;
    }

    //현재날짜는 문자열로 반환
    public static String getNowDateString()
    {

        Date date =  getNowDate();

        return dayFormatter.format(date);
    }

    //문자열을 날짜로 변환
    public static Date stringToDate(String data)
    {
        Date date = new Date();
        try
        {
            date = dayFormatter.parse(data);
        }
        catch (Exception e)
        {
            System.out.println("날짜 변환 실패");
            System.out.println(e.getMessage());
        }
        return date;
    }

    //날짜 비교 해주는 거
    //0 진행중
    //1 모집중
    //2 종료됨
    public static int compareDate(String startPoint, String endPoint)
    {

        Date now = getNowDate();
        Date start_point = stringToDate(startPoint);
        Date end_point = stringToDate(endPoint);

        int start_result = start_point.compareTo(now);
        int end_result = end_point.compareTo(now);

        int state_code = 0;

        if(start_result > 0) 
        {
            //System.out.println("모집중");
            state_code = 1;
        }
        else if(end_result < 0)
        {
            //System.out.println("종료됨");
            state_code = 2;
        }

        return state_code;
    }
}
