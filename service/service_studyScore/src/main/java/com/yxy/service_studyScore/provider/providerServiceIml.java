package com.yxy.service_studyScore.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.yxy.service_studyScore.service.ServiceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class providerServiceIml implements providerService{

    @Autowired
    ServiceUserService serviceUserService;
    @Override
    public List t() {
        return null;
    }
}
