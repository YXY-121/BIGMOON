package com.yxy.service_userCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_userCenter.bean.ServiceTotalscore;
import com.yxy.service_userCenter.mapper.ServiceTotalscoreMapper;
import com.yxy.service_userCenter.service.ServiceTotalscoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 总成绩表 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-12
 */
@Service
public class ServiceTotalscoreServiceImpl extends ServiceImpl<ServiceTotalscoreMapper, ServiceTotalscore> implements ServiceTotalscoreService {

    @Override
    public void saveScore(String schoolName, String department, String userId, int year, float studyScore) {
            ServiceTotalscore score=new ServiceTotalscore();
            score.setSchoolName(schoolName);
            score.setDepartment(department);
            score.setUserId(userId);
            score.setStatus("uncheck");
            score.setStudyscore(studyScore);
            score.setYear(year);

            this.save(score);

    }

    @Override
    public ServiceTotalscore isExistTotalScore(String schoolName, String userId, int year) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("userId",userId);
        wrapper.eq("year",year);
        ServiceTotalscore one = this.getOne(wrapper);
        return  one;

    }

    @Override
    public void updateScore(ServiceTotalscore totalscore,float newScore) {
        UpdateWrapper update=new UpdateWrapper();
        //set要修改的值
        update.set("selfStudyScore",newScore);
        //查询语句
        update.eq("year",totalscore.getYear());
        update.eq("userId",totalscore.getUserId());
        update.eq("schoolName",totalscore.getSchoolName());
        totalscore.setStudyscore(newScore);
        this.update(update);

    }


}
