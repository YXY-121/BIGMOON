package com.yxy.service_userCenter.service;

import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_userCenter.bean.ServiceLessonscore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_userCenter.bean.front.lessonScoreVo;
import com.yxy.service_userCenter.bean.ColumnScore;
import com.yxy.service_userCenter.bean.front.scoreTable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-12
 */
public interface ServiceLessonscoreService extends IService<ServiceLessonscore> {


     String dealFormula(String formula,List<ColumnScore> columnScores);

     List<ColumnScore>  sumRowScores(List<ColumnScore> columnScores);

    String GetFormula(String schoolName,String department) throws zongceException;

    //获取课程属性
    List<String> getLessonAttribute(String schoolName, String department) throws zongceException;
    //获取课程名称
    List<String> getLessonName(String schoolName, String department,String major,int year) throws zongceException;

    Float calc(String newFormula);

    void saveScore(String school, String department, String userId, int year, float totalStudyScore);
    void savePicture(String school,String userId,int year, List<String> file);
    boolean isExistScore(String school,String userId,int year);
    void updateScore(String school, String department, String userId, int year, float totalStudyScore);

}
