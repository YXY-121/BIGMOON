package com.yxy.service_studyScore.service;

import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_studyScore.bean.front.initAttribute;
import com.yxy.service_studyScore.exception.zongceException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceLessonattributeService extends IService<ServiceLessonattribute> {

    List<ServiceLessonattribute>isLessonAttributeExist(String school,String department);

    List<ServiceLessonattribute> addInitAttribute(initAttribute initAttribute) throws zongceException;

    List<ServiceLessonattribute> getAttributeByAdmin(String schoolName, String department) throws zongceException;

    boolean updateAtrribute(String name, String schoolName, String changedItem, String newValue);


    boolean deleteLessonAttribute(String schoolName, String department, String deleteItem);

    List<String> getLessonAttribute(List<ServiceLessonattribute> serviceLessonattributes);
}
