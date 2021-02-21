package com.yxy.service_self.mapper;

import com.yxy.service_self.bean.ServiceDeadline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 自测截止审核时间表 Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Mapper
@Repository
public interface ServiceDeadlineMapper extends BaseMapper<ServiceDeadline> {

}
