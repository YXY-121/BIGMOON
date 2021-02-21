package com.yxy.service_studyScore.service;

import com.yxy.service_studyScore.bean.ServiceScoretype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_studyScore.bean.front.initScopeType;

import com.yxy.service_studyScore.exception.zongceException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceScoretypeService extends IService<ServiceScoretype> {

   List<ServiceScoretype> isExistSchoolMajor(String school, String department);


    void addInitScopeType(initScopeType scopeType) throws zongceException;

    List<ServiceScoretype> getScopeTypeByAdmin(String schoolName, String department) throws zongceException;

    boolean updateScoretype(String schoolName, String department, String changedItem, Float changerValue,String newItem,Float newValue);

    boolean deleteScoreType(String schoolName, String department, String deleteItem);
}
