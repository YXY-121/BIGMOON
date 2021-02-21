package com.yxy.service_self.service;

import com.yxy.service_self.bean.ServiceDeadline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_self.bean.vo.voServiceDeadline.VoServiceDeadline;
import com.yxy.service_self.bean.vo.voServiceDeadline.VoServiceDeadlineto;

import java.util.List;

/**
 * <p>
 * 自测截止审核时间表 服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceDeadlineService extends IService<ServiceDeadline> {

    List<VoServiceDeadlineto> selectDeadline(VoServiceDeadline voServiceDeadline);

    //int updateDeadline(VoServiceDeadlineto voServiceDeadlineto);
}
