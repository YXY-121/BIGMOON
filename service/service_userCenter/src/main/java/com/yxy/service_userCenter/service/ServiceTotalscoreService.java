package com.yxy.service_userCenter.service;

import com.yxy.service_userCenter.bean.ServiceTotalscore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 总成绩表 服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-12
 */

public interface ServiceTotalscoreService extends IService<ServiceTotalscore> {

    void saveScore(String schoolName, String department, String userId, int year, float studyScore);


    void updateScore(ServiceTotalscore totalscore,float newScore);

    ServiceTotalscore isExistTotalScore(String schoolName, String userId, int year);
}
