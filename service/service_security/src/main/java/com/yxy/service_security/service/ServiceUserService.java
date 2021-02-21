package com.yxy.service_security.service;

import com.yxy.service_security.bean.ServiceUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-09
 */
public interface ServiceUserService extends IService<ServiceUser> {
    ServiceUser IsExistUser(String name,String school);

}
