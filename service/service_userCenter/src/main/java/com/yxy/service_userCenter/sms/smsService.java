package com.yxy.service_userCenter.sms;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Service;

@Service
public interface smsService {
    boolean sendSms(String phone, String code) throws ClientException;

}
