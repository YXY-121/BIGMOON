package com.yxy.service_studyScore.bean.excel;
import com.alibaba.excel.exception.ExcelAnalysisStopException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yxy.service_studyScore.bean.ServiceLesson;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.excel.*;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.yxy.service_studyScore.bean.front.initLessonVo;

import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.ServiceLessonService;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExcelListener extends AnalysisEventListener<LessonExcel> {
    public String school, department;



    public ServiceLessonService lessonService;
    public String isRepeat;
    public String isNull;
    public ExcelListener(String school,String department, ServiceLessonService lessonService) {
           this.department=department;
           this.school=school;
           this.lessonService=lessonService;


    }





    @Override
    public void invoke(LessonExcel lessonExcel, AnalysisContext analysisContext) {

        String lessonName = lessonExcel.getLessonName();
        System.out.println(lessonName);
/*
        //添加课程属性
        if(!StringUtils.isEmpty(attributes)){
            System.out.println(attributes);
            ServiceLessonattribute lessonattribute=new ServiceLessonattribute();

            lessonattribute.setAttributeName(attributes);

            //将schoolid和majorid注入lessonattribute
            BeanUtils.copyProperties(lessonVo,lessonattribute);

            //先查询是否存在 存在就跑错
            List<ServiceLessonattribute> lessonAttributeExist = lessonattributeService.isLessonAttributeExist(lessonattribute.getSchoolName(), lessonattribute.getDepartment());
            if (lessonAttributeExist==null){
             throw new zongceException("已经存在了该属性");
         }
            //保存lesson属性
            lessonattributeService.save(lessonattribute);

        }*/


            if(lessonName!=null&&!lessonName.isEmpty()){
                ServiceLesson lesson=new ServiceLesson();
                lesson.setLessonName(lessonName);
                lesson.setSchoolName(school);
                lesson.setDepartment(department);
                //将school和major和grade注入lesson

                boolean lessonExist = lessonService.isLessonExist(lesson);
                if (lessonExist){
                  // throw new ExcelAnalysisStopException();
                }else{
                    lessonService.save(lesson);

                }

        }
            else
                throw new ExcelAnalysisStopException();





    }

    //读取完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        throw exception;
    }

}

