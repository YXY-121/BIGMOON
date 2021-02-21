package com.yxy.service_studyScore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.front.initAttribute;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.mapper.ServiceLessonattributeMapper;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@com.alibaba.dubbo.config.annotation.Service

@Service
public class ServiceLessonattributeServiceImpl extends ServiceImpl<ServiceLessonattributeMapper, ServiceLessonattribute> implements ServiceLessonattributeService {

    @Override
    public List<ServiceLessonattribute> isLessonAttributeExist(String school,String department) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",school);
        wrapper.eq("department",department);
        List<ServiceLessonattribute> one =this.list(wrapper);
  return one;
    }

    @Override
    public List<ServiceLessonattribute> addInitAttribute(initAttribute initAttribute) throws zongceException {
         String school=initAttribute.getSchoolName();
         String department=initAttribute.getDepartment();
        List<String> lessonattributes = initAttribute.getLessonattributes();


        //查出已有属性 判断是否有重复的
        List<ServiceLessonattribute> lessonAttributeExist = isLessonAttributeExist(school, department);
        for(String s:lessonattributes)    {
            for(ServiceLessonattribute a:lessonAttributeExist){
                if(s.equals(a.getAttributeName())){
                    throw  new zongceException("已经有该属性-");
                }
            }
        }


        //没有就存
        for(String s:lessonattributes){
            ServiceLessonattribute temp=new ServiceLessonattribute();
            //不存在就添加，存在就跳过
            temp.setSchoolName(school);
            temp.setDepartment(department);
            temp.setAttributeName(s);
            this.save(temp);
        }

        //查询出所有的属性
     return getAttributeByAdmin(school,department);

    }

    @Override
    public List<ServiceLessonattribute> getAttributeByAdmin(String schoolName, String department) throws zongceException {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        List<ServiceLessonattribute> list = this.list(wrapper);
        if(list==null){
            throw new zongceException("找不到该院校属性");
        }
        return list;
    }

    @Override
    public boolean updateAtrribute(String schoolName, String department, String changedItem, String newValue) {
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("schoolName",schoolName);
        updateWrapper.eq("department",department);
        updateWrapper.eq("attributeName",changedItem);
        ServiceLessonattribute a=new ServiceLessonattribute();
        a.setAttributeName(newValue);
        a.setDepartment(department);
        a.setSchoolName(schoolName);
     return   this.update(a,updateWrapper);

      //  return this.update(updateWrapper);

    }

    @Override
    public boolean deleteLessonAttribute(String schoolName, String department, String deleteItem) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        wrapper.eq("attributeName",deleteItem);
        return this.remove(wrapper);
    }

    @Override
    public List<String> getLessonAttribute(List<ServiceLessonattribute> serviceLessonattributes) {
        List<String>  attribute= new ArrayList();
        for(ServiceLessonattribute attributeName:serviceLessonattributes)
            attribute.add(attributeName.getAttributeName());
        return attribute;
    }


}
