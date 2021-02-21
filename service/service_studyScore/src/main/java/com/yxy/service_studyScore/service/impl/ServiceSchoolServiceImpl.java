package com.yxy.service_studyScore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_studyScore.bean.ServiceSchool;

import com.yxy.service_studyScore.bean.front.initschooldepartment;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.mapper.ServiceSchoolMapper;
import com.yxy.service_studyScore.service.ServiceSchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ServiceSchoolServiceImpl extends ServiceImpl<ServiceSchoolMapper, ServiceSchool> implements ServiceSchoolService {



    @Override
    public boolean isExistSchoolDepartment(String schoolName,String department) throws zongceException {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        ServiceSchool one =  this.getOne(wrapper);
        if(one!=null){
            throw  new zongceException("已有该学校");
        }
        else {
            ServiceSchool school=new ServiceSchool();
            school.setDepartment(department);
            school.setSchoolName(schoolName);
           this.save(school);
        }
        return false;

    }

  /*  @Override
    public void initBasicMsg(initschooldepartment vo) throws zongceException {
        ServiceSchool s=new ServiceSchool();

        for(String major:majors) {
            boolean existSchoolMajorGrade = isExistSchoolDepartment(vo.getSchoolName(), vo.getDepartment());
            if(existSchoolMajorGrade)
                throw new zongceException("已经有了该学校该院的专业");
        }
        for(String major:majors) {
           ServiceSchool school=new ServiceSchool();
           school.setMajorName(major);
           school.setSchoolName(vo.getSchoolName());
           school.setDepartment(vo.getDepartment());

            this.save(school);
        }


    }*/
}
