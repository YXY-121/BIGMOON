package com.yxy.service_self.service;

import com.yxy.service_self.bean.ServiceTotalscore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoServiceTotalscore;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoServiceTotalscoreto;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoUpdatetatolscore;

import java.util.List;

/**
 * <p>
 * 总成绩表 服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceTotalscoreService extends IService<ServiceTotalscore> {

    List<VoServiceTotalscoreto> selectTotalAll(VoServiceTotalscore voServiceTotalscore);
    List<VoServiceTotalscoreto> selectTotalSelf(VoServiceTotalscore voServiceTotalscore);
    List<VoServiceTotalscoreto> selectTotalSuper1(VoServiceTotalscore voServiceTotalscore);
    List<VoServiceTotalscoreto> selectTotalSuper2(VoServiceTotalscore voServiceTotalscore);
    void updateTotal(VoUpdatetatolscore voUpdatetatolscore);
    //综测总成绩计算接口
    void totalScore(String userId,String schoolName,String  year);
}
