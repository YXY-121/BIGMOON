package com.yxy.service_self.service;

import com.yxy.service_self.bean.ServiceAddscoretype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_self.bean.vo.voAddscoretype.*;

import java.util.List;

/**
 * <p>
 * 自测加分大类表 服务类
 * </p>
 *
 * @author wlh
 * @since 2020-12-13
 */
public interface ServiceAddscoretypeService extends IService<ServiceAddscoretype> {

    void  saveadd(List<VoAddscoretype> voAddscoretype) ;

    List<VoselectAddscoretypeselectto> ServiceAddscoretypeSelect(VoselectAddscoretypeselect1 voselectAddscoretypeselect1);

    List<VoselectAddscoretypeselectto1> ServiceAddscoretypeSelect1(VoselectAddscoretypeselect1 voselectAddscoretypeselect1);

    List<VoselectAddscoretypeselectto3> ServiceAddscoretypeSelectself(VoselectAddscoretypeselect voselectAddscoretypeselect);

    void ServiceAddscoretypeupdate(VoAddscoretypeupdate voAddscoretypeupdate);

    void saveadd1(List<VoAddscoretype1> voAddscoretype1);

    void ServiceAddscoretypedelete(VoAddscoretypedelete voAddscoretypedelete);
}
