package com.yxy.service_security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_security.bean.ServiceUser;
import com.yxy.service_security.mapper.ServiceUserMapper;
import com.yxy.service_security.service.ServiceUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-09
 */
@Service
public class ServiceUserServiceImpl extends ServiceImpl<ServiceUserMapper, ServiceUser> implements ServiceUserService {

    @Override
    public ServiceUser IsExistUser(String id, String school) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userId",id);
        wrapper.eq("schoolName",school);
        ServiceUser user = this.getOne(wrapper);
        return user;
    }
}
