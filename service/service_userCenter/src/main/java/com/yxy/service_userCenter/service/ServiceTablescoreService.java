package com.yxy.service_userCenter.service;

import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_userCenter.bean.ColumnScore;
import com.yxy.service_userCenter.bean.ServiceTablescore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_userCenter.bean.front.scoreTable;
import com.yxy.service_userCenter.bean.rowScore;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2021-02-02
 */
public interface ServiceTablescoreService extends IService<ServiceTablescore> {
    void insertLessonTableScore(List<scoreTable> table, String userId,int year);
    boolean isExistTableScore(String userId,int year);

    void updateLessonTableScore(List<scoreTable> table, String id,int year);
    List<ColumnScore> convertTableScoreTORowScore(List<String>  attribute, List<scoreTable> table);
}
