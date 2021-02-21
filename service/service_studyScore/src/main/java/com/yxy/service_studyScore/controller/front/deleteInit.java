package com.yxy.service_studyScore.controller.front;

import com.yxy.service_studyScore.bean.ServiceFormula;
import com.yxy.service_studyScore.bean.ServiceScoretype;
import com.yxy.service_studyScore.bean.front.deleteVo;
import com.yxy.service_studyScore.bean.front.schoolDepartmentVo;
import com.yxy.service_studyScore.bean.front.updateVo;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.ServiceFormulaService;
import com.yxy.service_studyScore.service.ServiceLessonService;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import com.yxy.service_studyScore.service.ServiceScoretypeService;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("service_studyScore/deleteInit")

@RestController
@CrossOrigin
public class deleteInit {
    @Autowired
    ServiceFormulaService formulaService;
    @Autowired
    ServiceLessonService lessonService;
    @Autowired
    ServiceLessonattributeService lessonattributeService;
    @Autowired
    ServiceScoretypeService scoretypeService;

    @PostMapping("deleteFormula")
    public ResultUtils deleteFormula(@RequestBody schoolDepartmentVo vo) {
        boolean flag = formulaService.deleteFormula(vo.getSchoolName(), vo.getDepartment());
        if (flag)
            return ResultUtils.OK().data("values", formulaService.getFormulaByAdmin(vo.getSchoolName(), vo.getDepartment()));
        else
            return ResultUtils.error().message("删除失败");
    }
    @PostMapping("deleteLesson")
    public ResultUtils deleteLesson(@RequestBody deleteVo vo) {
        boolean flag = lessonService.deleteLesson(vo.getSchoolName(), vo.getDepartment(), vo.getDeleteItem());
        if (flag)
            return ResultUtils.OK().data("values", lessonService.getLessonByAdmin(vo.getSchoolName(), vo.getDepartment()));
        else
            return ResultUtils.error().message("删除失败");
    }

    @PostMapping("deleteLessonAttribute")//todo
    public ResultUtils deleteLessonAttribute(@RequestBody deleteVo vo) {
        boolean flag = lessonattributeService.deleteLessonAttribute(vo.getSchoolName(), vo.getDepartment(), vo.getDeleteItem());
        if (flag) {
            try {
                //删除lessonAttribute后还要把带有该删除的item的公式给删了
                ServiceFormula formula=formulaService.getFormulaByAdmin(vo.getSchoolName(),vo.getDepartment());
                boolean isFormulaRight=formulaService.isWrongAttribute(formula.getFormula(),vo.getSchoolName(),vo.getDepartment());
                if(!isFormulaRight)
                    formulaService.deleteFormula(vo.getSchoolName(),vo.getDepartment());
                return ResultUtils.OK().data("values", lessonattributeService.getAttributeByAdmin(vo.getSchoolName(), vo.getDepartment()));
            } catch (zongceException e) {
                e.printStackTrace();
                return ResultUtils.error().message("删除失败");
            }
        }
        else
            return ResultUtils.error().message("删除失败");
    }

    @PostMapping("deleteScoreType")
    public ResultUtils deleteScoreType(@RequestBody deleteVo vo) {
        boolean flag = scoretypeService.deleteScoreType(vo.getSchoolName(), vo.getDepartment(), vo.getDeleteItem());
        if (flag) {
            try {
                return ResultUtils.OK().data("values", scoretypeService.getScopeTypeByAdmin(vo.getSchoolName(), vo.getDepartment()));
            } catch (zongceException e) {
                e.printStackTrace();
                return ResultUtils.error().message("删除失败");

            }
        }
        else
            return ResultUtils.error().message("删除失败");
    }

}
