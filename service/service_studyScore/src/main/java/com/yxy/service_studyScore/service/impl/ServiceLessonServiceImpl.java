package com.yxy.service_studyScore.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.ServiceLesson;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.excel.ExcelListener;
import com.yxy.service_studyScore.bean.excel.LessonExcel;
import com.yxy.service_studyScore.bean.front.initLessonVo;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.mapper.ServiceLessonMapper;
import com.yxy.service_studyScore.service.ServiceLessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ServiceLessonServiceImpl extends ServiceImpl<ServiceLessonMapper, ServiceLesson> implements ServiceLessonService {



    public boolean isLessonExist(ServiceLesson lesson){
    QueryWrapper wrapper=new QueryWrapper();
    wrapper.eq("schoolName",lesson.getSchoolName());
    wrapper.eq("department",lesson.getDepartment());
    wrapper.eq("lessonName",lesson.getLessonName());
    ServiceLesson one = this.getOne(wrapper);
    if(one==null){
        return false;
    }
    return true;
}

    @Override
    public boolean isLessonExist(String school, String department) throws zongceException {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",school);
        wrapper.eq("department",department);
        List<ServiceLesson> list = this.list(wrapper);

        if( list.isEmpty()){
            throw new zongceException("找不到该院校课程");
        }
        return true;
    }

    @Override
    public List<ServiceLesson> getLessonByAdmin(String schoolName, String department)  {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        List<ServiceLesson> list = this.list(wrapper);
        return list;
    }

    @Override
    public boolean updateLesson(String schoolName, String department, String changedItem, String newValue) {
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("schoolName",schoolName);
        updateWrapper.eq("department",department);
        updateWrapper.eq("lessonName",changedItem);
        ServiceLesson a=new ServiceLesson();
        a.setLessonName(newValue);
        a.setDepartment(department);
        a.setSchoolName(schoolName);
        return   this.update(a,updateWrapper);
    }

    @Override
    public List<ServiceLesson> getLessonByUser(String schoolName, String department, String major, String year) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        wrapper.eq("majorName",major);
        wrapper.eq("year",year);
        return this.list(wrapper);

    }

    @Override
    public boolean deleteLesson(String schoolName, String department, String deleteItem) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        wrapper.eq("lessonName",deleteItem);
        return this.remove(wrapper);
    }

    @Override
    public boolean uploadLessonFile(String school,String department, MultipartFile file) {
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String isNull="false",isRepeat="false";

        EasyExcel.read(in, LessonExcel.class, new ExcelListener(school,department,this)).sheet().doRead();

       return  true;
    }
}
