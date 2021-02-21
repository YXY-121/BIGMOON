package com.yxy.service_userCenter.bean.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_userCenter.bean.ServiceUser;
import com.yxy.service_userCenter.service.ServiceUserService;
import com.zongce.serviceBase.exception.zongceException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;


public class  UserExcelListener extends AnalysisEventListener<UserExcel> {

    private ServiceUserService userService;
    private String school;
    private String pref;
    public UserExcelListener(ServiceUserService userService,String school, String pref){
        this.userService=userService;
        this.school=school;
        this.pref=pref;
        }


  @Override
  public void invoke(UserExcel userExcel, AnalysisContext analysisContext) {
        String userId=userExcel.getUserId();
        if(!(StringUtils.isEmpty(userId)&&StringUtils.isEmpty(userExcel.getSchoolName()))){//学校或者学号空的话就不插入
            ServiceUser user=new ServiceUser();
            BeanUtils.copyProperties(userExcel,user);

            user.setRoleId("user");
            user.setPassword(pref+user.getUserId());
            user.setSchoolName(school);
            userService.saveOrUpdate(user);

        }


  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
 }
