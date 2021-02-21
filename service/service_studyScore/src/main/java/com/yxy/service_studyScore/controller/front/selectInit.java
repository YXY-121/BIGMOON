package com.yxy.service_studyScore.controller.front;

import com.yxy.service_studyScore.bean.*;

import com.yxy.service_studyScore.bean.front.schoolDepartmentVo;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.*;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("service_studyScore/selectAllInit")
@RestController
@CrossOrigin
public class selectInit{
    @Autowired
    ServiceLessonService lessonService;

    @Autowired
    ServiceLessonattributeService lessonattributeService;

    @Autowired
    ServiceFormulaService formulaService;
    @Autowired
    ServiceScoretypeService scoretypeService;


    public ResultUtils checkMySetting(@RequestBody ServiceUser admin){
        //年份

        //查看本院所有同学成绩

        //查看本院某专业的成绩

        //查看本院某专业某班的成绩

        //查看本院某专业某班某个同学的成绩
return ResultUtils.OK();
    }

    @PostMapping("selectFormula")
    public ResultUtils selectFormula(@RequestBody schoolDepartmentVo admin) throws zongceException {

        //查看公式设置
      ServiceFormula formula= formulaService.getFormulaByAdmin(admin.getSchoolName(),admin.getDepartment());
        if(formula==null){
            throw new zongceException("查无此公式");
        }
        return ResultUtils.OK().message(formula.getFormula());
    }


    @PostMapping("selectAttribute")
    public ResultUtils selectAttribute(@RequestBody schoolDepartmentVo admin) throws zongceException {

        //查看课程属性设置

        List<ServiceLessonattribute> a = lessonattributeService.getAttributeByAdmin(admin.getSchoolName(),admin.getDepartment());
            if(a.isEmpty()){
                return ResultUtils.error().message("查无此课程属性");
            }


        List <String>list=new ArrayList();
        for(ServiceLessonattribute s:a){
            list.add(s.getAttributeName());
        }
        return ResultUtils.OK().data("Attributes",list);
    }


    @PostMapping("selectScopeType")

    public ResultUtils selectScopeType(@RequestBody schoolDepartmentVo admin) {

        //查看课程属性设置
        List<ServiceScoretype> a= null;
        try {
            a = scoretypeService.getScopeTypeByAdmin(admin.getSchoolName(),admin.getDepartment());
            if(a.isEmpty()){
                throw new zongceException("查无此综测分类");
            }
        } catch (zongceException e) {
            e.printStackTrace();
            return ResultUtils.error().message("查无此综测分类");
        }

       List <String>list=new ArrayList();
        for(ServiceScoretype s:a){
            list.add(s.getScoreTypeName());
        }
        return ResultUtils.OK().data("ScopeTypes",list);
    }





    @PostMapping("selectLesson")
    public ResultUtils selectLesson(@RequestBody schoolDepartmentVo admin)  {
        //查看课程属性设置


            List<ServiceLesson> a = lessonService.getLessonByAdmin(admin.getSchoolName(), admin.getDepartment());
        if(a.isEmpty()){
            try {
                throw new zongceException("查无课程信息");
            } catch (zongceException e) {
                e.printStackTrace();
                return ResultUtils.error().message("查无课程信息");
            }
        }

            List <String>list=new ArrayList();
            for(ServiceLesson s:a){
                list.add(s.getLessonName());
            }
            return ResultUtils.OK().data("课程",list);


    }


}
