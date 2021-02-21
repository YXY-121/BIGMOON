package com.yxy.service_self.mapper;

import com.yxy.service_self.bean.ServiceSelftotalscore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxy.service_self.service.ServiceSelftotalscoreService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 自测总成绩表 Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Repository
@Mapper
public interface ServiceSelftotalscoreMapper extends BaseMapper<ServiceSelftotalscore> {

}
