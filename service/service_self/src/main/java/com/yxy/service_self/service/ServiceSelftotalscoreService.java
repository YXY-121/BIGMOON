package com.yxy.service_self.service;

import com.yxy.service_self.bean.ServiceSelftotalscore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscore;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscore1;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscoreto;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscoreto1;

import java.util.List;

/**
 * <p>
 * 自测总成绩表 服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceSelftotalscoreService extends IService<ServiceSelftotalscore> {

    List<VoServiceSelftotalscoreto> selectSelftotalscore(VoServiceSelftotalscore voServiceSelftotalscore);

    List<VoServiceSelftotalscoreto1> selectSelftotalscore1(VoServiceSelftotalscore voServiceSelftotalscore);

    List<VoServiceSelftotalscoreto1> selectSelftotalscore2(VoServiceSelftotalscore1 voServiceSelftotalscore1);

    List<VoServiceSelftotalscoreto1> selectSelftotalscore3(VoServiceSelftotalscore1 voServiceSelftotalscore2);
}
