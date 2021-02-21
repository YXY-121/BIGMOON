package com.yxy.service_self.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yxy.service_self.bean.ServiceSelftestmodule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 自测模块表 Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Mapper
@Repository
public interface ServiceSelftestmoduleMapper extends BaseMapper<ServiceSelftestmodule> {
    @Select("select sum(score) from service_selftestmodule ${ew.customSqlSegment}")//${ew.customSqlSegment} 不是必须写的。当你自定义方法还想用条件构造器的时候，才需要这样写。可以使用mybatis原生的方式。
    float sumScoreall(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
