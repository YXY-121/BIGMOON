package com.yxy.service_userCenter.controller.front;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.ServiceFormulaService;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import com.yxy.service_userCenter.bean.ServiceTotalscore;
import com.yxy.service_userCenter.bean.front.scoreTable;
import com.yxy.service_userCenter.bean.front.tableVo;
import com.yxy.service_userCenter.bean.front.userVo;
import com.yxy.service_userCenter.bean.ColumnScore;
import com.yxy.service_userCenter.service.ServiceLessonscoreService;
import com.yxy.service_userCenter.service.ServiceTablescoreService;
import com.yxy.service_userCenter.service.ServiceTotalscoreService;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@RequestMapping("service_userCenter/setScore")
@RestController
@CrossOrigin
public class score {
    @Autowired
    ServiceLessonscoreService lessonscoreService;
    @Autowired
    ServiceTotalscoreService totalscoreService;
    @Autowired
    ServiceTablescoreService tablescoreService;
  /*  @Autowired
    ossService ossService;*/
  @Reference
  ServiceLessonattributeService lessonattributeService;

    @PostMapping("getLessonScore&&LessonName")
    public ResultUtils getLessonScoreInit(@RequestBody userVo vo) {
        List<String> lessonAttribute = null;
        List<String> lessonName=null;
        try {
            lessonAttribute = lessonscoreService.getLessonAttribute(vo.getSchoolName(), vo.getDepartment());
            lessonName = lessonscoreService.getLessonName(vo.getSchoolName(), vo.getDepartment(), vo.getMajor(), vo.getYear());

        } catch (zongceException e) {
            e.printStackTrace();
        }
        return ResultUtils.OK().data("lessonAttribute", lessonAttribute).data("lessonName", lessonName).code(200);
    }

//    @PostMapping("setPicture")
//    public ResultUtils getPicture(MultipartFile file) {
//        String school="仲恺";
//        String userId="1";
//        int year=1;
//        List<String> fileUrl = ossService.uploadFileAvatar(file);
//        lessonscoreService.savePicture(school, userId ,year, fileUrl);
//        return ResultUtils.OK().data("图片地址为", "").code(200);
//
//    }

    @PostMapping("setScoreTable")
    @Transactional
    public ResultUtils setScoreTable(@RequestBody tableVo vo) throws zongceException {
        List<scoreTable> table = vo.getTable();
         String school=vo.getSchoolName();
         String department=vo.getDepartment();
         String userId=vo.getUserId();
         int year=vo.getYear();
        List<String> lessonAttribute = lessonattributeService.getLessonAttribute(lessonattributeService.getAttributeByAdmin(school,department));

        List<ColumnScore> columnScores = tablescoreService.convertTableScoreTORowScore(lessonAttribute, table);
        lessonscoreService.sumRowScores(columnScores);
        //找到公式（学业+绩点*0.3）
        String formula = lessonscoreService.GetFormula(school, department);

        //将（学业+绩点*0.3）替换成（75+45*0.3）
        String newFormula = lessonscoreService.dealFormula(formula, columnScores);

        //（75+45*0.3）计算这串数字的结果 得到学业分原始总分
        Float calc = lessonscoreService.calc(newFormula);

        //插入个人成绩表
        if(!tablescoreService.isExistTableScore(userId,year))//不存在就直接save
       {
           tablescoreService.insertLessonTableScore(table,userId,year);

       }
       else
           tablescoreService.updateLessonTableScore(table,userId,year);

       //将总分、图片插入每年的表- -
        boolean isExist=lessonscoreService.isExistScore(school,userId,year);
        if(!isExist){
            //保存住学业分原始总分
            lessonscoreService.saveScore(school, department, userId,year,calc);
        }
        else
            lessonscoreService.updateScore(school,department,userId,year,calc);

       //插入综测总表
        //先判断有没有这个人的学习成绩 如果有就变成修改，没有就savaScore
        ServiceTotalscore existScore = totalscoreService.isExistTotalScore(vo.getSchoolName(), vo.getUserId(), vo.getYear());


        if (existScore != null) {
            totalscoreService.updateScore(existScore, calc);

        } else
        // 学习成绩插入到总表里头去
        {
            totalscoreService.saveScore(vo.getSchoolName(), vo.getDepartment(),vo.getUserId(),vo.getYear(),calc);
        }

        return ResultUtils.OK().data("",columnScores).data("学习成绩总分是", calc);
    }

}
