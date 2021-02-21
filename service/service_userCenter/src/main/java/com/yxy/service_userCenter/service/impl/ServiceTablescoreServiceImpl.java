package com.yxy.service_userCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_userCenter.bean.ColumnScore;
import com.yxy.service_userCenter.bean.ServiceTablescore;
import com.yxy.service_userCenter.bean.ServiceTotalscore;
import com.yxy.service_userCenter.bean.front.lessonAttributeScore;
import com.yxy.service_userCenter.bean.front.scoreTable;
import com.yxy.service_userCenter.bean.rowScore;
import com.yxy.service_userCenter.mapper.ServiceTablescoreMapper;
import com.yxy.service_userCenter.service.ServiceTablescoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2021-02-02
 */
@Service
public class ServiceTablescoreServiceImpl extends ServiceImpl<ServiceTablescoreMapper, ServiceTablescore> implements ServiceTablescoreService {

    @Override
    public void insertLessonTableScore(List<scoreTable> table, String userId,int year) {
        for (scoreTable tableTmp : table) {
            String lesson = tableTmp.getLessonName();
            List<lessonAttributeScore> lessonAttributeValue = tableTmp.getLessonAttributeValue();
            for (lessonAttributeScore map : lessonAttributeValue) {
                float score = map.getScore();
                ServiceTablescore tablescore = new ServiceTablescore();
                tablescore.setUserId(userId);
                tablescore.setLessonName(lesson);
                tablescore.setAttributeName(map.getAttribute());
                tablescore.setScore(score);
                tablescore.setYear(year);
                this.save(tablescore);

            }
        }


    }

    @Override
    public boolean isExistTableScore(String userId,int year) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userId", userId);
        wrapper.eq("year",year);
        if (!this.list(wrapper).isEmpty())
        {
            System.out.println("已经存在，调用修改！");
            return true;
        }
        return false;
    }

    @Override
    public void updateLessonTableScore(List<scoreTable> table, String userId,int year) {
        for (scoreTable tableTmp : table) {
            String lesson = tableTmp.getLessonName();
            List<lessonAttributeScore> lessonAttributeValue = tableTmp.getLessonAttributeValue();
            for (lessonAttributeScore map : lessonAttributeValue) {
                float score = map.getScore();
                UpdateWrapper wrapper=new UpdateWrapper();
                wrapper.eq("userId",userId);
                wrapper.eq("lessonName",lesson);
                wrapper.eq("attributeName",map.getAttribute());
                wrapper.eq("year",year);

                wrapper.set("score",score);

                this.update(wrapper);

            }
        }
    }

    @Override
    public List<ColumnScore> convertTableScoreTORowScore(List<String> attribute, List<scoreTable> table) {


        List<ColumnScore> columnScores=new ArrayList<>();
            for(String s:attribute){
                ColumnScore columnScore=new ColumnScore();
                List<Float> score=new ArrayList<>();
                //每一行
                for(scoreTable table1:table){
                    //每一行的每个属性
                    List<lessonAttributeScore> lessonAttributeValue = table1.getLessonAttributeValue();

                    for(lessonAttributeScore lessonAttributeScore:lessonAttributeValue){
                        System.out.println(s+"  "+lessonAttributeScore);
                        if(s.equals(lessonAttributeScore.getAttribute())){
                            score.add(lessonAttributeScore.getScore());
                        }
                    }
                }
            //通过遍历attribute 来寻找每一个
                columnScore.setScores(score);
                columnScore.setLessonAttribute(s);
                System.out.println(score+"    "+columnScore.getLessonAttribute());
                columnScores.add(columnScore);
            }

        return columnScores;
    }

}