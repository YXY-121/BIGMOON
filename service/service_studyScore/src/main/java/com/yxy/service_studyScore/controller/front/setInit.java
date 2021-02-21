package com.yxy.service_studyScore.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_studyScore.bean.ServiceFormula;
import com.yxy.service_studyScore.bean.ServiceLesson;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.ServiceScoretype;
import com.yxy.service_studyScore.bean.front.*;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.*;

import com.zongce.serviceBase.resultCode.ResultUtils;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RequestMapping("service_studyScore/setInit")
@CrossOrigin
@RestController
public class setInit {

    @Autowired
    ServiceSchoolService schoolService;
    @Autowired
    ServiceScoretypeService scoretypeService;
    @Autowired
    ServiceLessonService lessonService;
    @Autowired
    ServiceFormulaService formulaService;
    @Autowired
    ServiceAdminService adminService;
    @Autowired
    ServiceLessonattributeService lessonattributeService;

    @PostMapping("setSchool&&Department")//第一步
    @Transactional
    public ResultUtils initBasicMsg(@RequestBody initschooldepartment vo) {
        try {
            boolean existSchoolDepartment = schoolService.isExistSchoolDepartment(vo.getSchoolName(), vo.getDepartment());
        } catch (zongceException e) {
            e.printStackTrace();
            return ResultUtils.error().message("已有该学校的该院系");

        }

        return ResultUtils.OK().message("设置成功");

    }

    //已经测试
    @PostMapping("setLesson")
    @Transactional
    //初始化院系的课程属性 和整个院系课程
    public ResultUtils initLessonMsg(initLessonVo lessonVo, MultipartFile file) {
        try {
            lessonService.uploadLessonFile(lessonVo.getSchoolName(), lessonVo.getDepartment(), file);

        } catch (Exception e) {
            return ResultUtils.error().message("有为空课程");

        }
        return ResultUtils.OK().message("设置课程成功");

    }
    @PostMapping("uploadLessonFile")
    @Transactional
    //初始化院系的课程属性 和整个院系课程
    public ResultUtils uploadLessonFile(initLessonVo lessonVo, MultipartFile file) {
        try {
            lessonService.uploadLessonFile(lessonVo.getSchoolName(), lessonVo.getDepartment(), file);

        } catch (Exception e) {
            return ResultUtils.error().message("有为空课程");

        }
        return ResultUtils.OK().message("设置课程成功");

    }

    @PostMapping("setLessonAttribute")//已经测试
    public ResultUtils setAttribute(@RequestBody initAttribute initAttribute) {
        List<ServiceLessonattribute> list = null;
        try {
            list = lessonattributeService.addInitAttribute(initAttribute);
        } catch (zongceException e) {
            e.printStackTrace();
            return ResultUtils.error().message("课程属性设置失败 已存在属性");

        }
        return ResultUtils.OK().data("你的课程属性", list);

    }


    @PostMapping("setScopeType")//已经测试
    public ResultUtils setScopeType(@RequestBody initScopeType scopeType) {
        try {
            scoretypeService.addInitScopeType(scopeType);
        } catch (zongceException e) {
            e.printStackTrace();
            return ResultUtils.OK().message("已设置了综测大类项目=");

        }
        return ResultUtils.OK().message("设置成功");

    }

    @PostMapping("setFormula")//已经测试
    //设置整个院的学习计算公式
    public ResultUtils getformula(@RequestBody initFormulaVo formula) {


        //判断公式有没有错误！全是属性的

        //判断公式是否存在：返回公式并且出现一个修改的按钮；
        ServiceFormula formulaBean = formulaService.isFormulaExist(formula);
        if (formulaBean != null) {
            return ResultUtils.error().data("该院校已有公式", formulaBean);
        }

        //以及属性是否错误
        if (!formulaService.isWrongAttribute(formula.getFormula(), formula.getSchoolName(), formula.getDepartment()))
            {
                ServiceFormula serviceFormula = new ServiceFormula();
                BeanUtils.copyProperties(formula, serviceFormula);
                formulaService.save(serviceFormula);
                return ResultUtils.OK().message("公式插入正确");
            }




        return ResultUtils.error().message("公式插入错误,有未知属性");

    }
}