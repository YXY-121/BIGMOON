package com.yxy.service_studyScore.service;

import com.yxy.service_studyScore.bean.ServiceSchool;
import com.baomidou.mybatisplus.extension.service.IService;

import com.yxy.service_studyScore.bean.front.initschooldepartment;
import com.yxy.service_studyScore.exception.zongceException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceSchoolService extends IService<ServiceSchool> {


    boolean isExistSchoolDepartment(String schoolName, String department) throws zongceException;

    //void initBasicMsg(initschooldepartment vo) throws zongceException;

}
