package com.yxy.service_userCenter.bean.excel;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class excelVo {
    public String schoolName;
    public String pref;
    MultipartFile file;
}
