package com.yxy.service_userCenter.service;

import com.aliyuncs.exceptions.ClientException;
import com.yxy.service_userCenter.bean.ServiceUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_userCenter.bean.ColumnScore;
import com.zongce.serviceBase.exception.zongceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-11
 */
public interface ServiceUserService extends IService<ServiceUser> {

    ColumnScore sumScore(ColumnScore columnScore);

    List<ColumnScore> sumScores(List<ColumnScore> columnScores);

    float calc(String newFormula);

    //替换公式
    String insteadFormula(List<ColumnScore> columnScores, String formula);
    //获取公式
    String GetFormula(String schoolName,String department) throws zongceException;

    //获取课程属性
    List<String> getLessonAttribute(String schoolName, String department) throws zongceException, com.yxy.service_studyScore.exception.zongceException;
    //获取课程名称
    List<String> getLessonName(String schoolName, String department,String major,String year) throws zongceException;

    boolean isExistStudent(String schoolName, String userId);

    boolean updateSelfPwd(String schoolName, String userId, String pwd) ;
    boolean checkCode(String phone,String code);
    boolean sendCode(String phone);
    String getPhone(String schoolName,String userId);


    List<ServiceUser> getUserMegByAdmin(String schoolName, String department);

    boolean updateUserMeg(String schoolName, String department, String changedItem, String newValue);

    void uploadUserFile(String schoolName, String pref, MultipartFile file);
}
