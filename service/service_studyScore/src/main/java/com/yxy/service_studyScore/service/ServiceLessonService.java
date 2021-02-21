package com.yxy.service_studyScore.service;

import com.yxy.service_studyScore.bean.ServiceLesson;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_studyScore.bean.front.initLessonVo;
import com.yxy.service_studyScore.exception.zongceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceLessonService extends IService<ServiceLesson> {

    boolean uploadLessonFile(String schoolName, String department, MultipartFile file);
     boolean isLessonExist(ServiceLesson lesson);
    boolean isLessonExist(String school,String department) throws zongceException;
    List<ServiceLesson> getLessonByAdmin(String schoolName, String department) ;

    boolean updateLesson(String schoolName, String department, String changedItem, String newValue);

    List<ServiceLesson> getLessonByUser(String schoolName, String department, String major, String year);

    boolean deleteLesson(String schoolName, String department, String deleteItem);
}
