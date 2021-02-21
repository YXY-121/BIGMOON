package com.yxy.service_self.mapper;

import com.yxy.service_self.bean.ServiceTotalscore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 总成绩表 Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Repository
@Mapper
public interface ServiceTotalscoreMapper extends BaseMapper<ServiceTotalscore> {

}
