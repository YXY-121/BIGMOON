package com.yxy.service_self.service;

import com.yxy.service_self.bean.ServiceSelftestmodule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_self.bean.vo.voServiceSelftestmodule.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 自测模块表 服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceSelftestmoduleService extends IService<ServiceSelftestmodule> {

   List<voServiceSelftestmoduleto> saveadd(List<voServiceSelftestmodule> voServiceSelftestmodule);

   List<String> upload(MultipartFile[] files);

    void deletefiles(List<String> urls);

    void deleteself(List<voServiceSelftestmoduledelete> voServiceSelftestmoduledelete);

    void updateself(List<voServiceSelftestmoduleupdate> voServiceSelftestmoduleupdate);
//需要返回年份的
    List<voServiceSelftestmoduleto1>  selectself(voServiceSelftestmoduleselect voServiceSelftestmoduleselect);
    //不需要返回年份的
    List<voServiceSelftestmoduleto2> selectself1(voServiceSelftestmoduleselect voServiceSelftestmoduleselect);
    //不需要返回年份的
    List<voServiceSelftestmoduleto2> selectself2(voServiceSelftestmoduleselect1 voServiceSelftestmoduleselect1);
    //不需要返回年份的
    List<voServiceSelftestmoduleto2> selectself3(voServiceSelftestmoduleselect2 voServiceSelftestmoduleselect2);
}
