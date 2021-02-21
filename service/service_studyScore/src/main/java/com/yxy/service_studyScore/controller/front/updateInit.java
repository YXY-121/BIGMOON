package com.yxy.service_studyScore.controller.front;

import com.yxy.service_studyScore.bean.ServiceFormula;
import com.yxy.service_studyScore.bean.front.initFormulaVo;
import com.yxy.service_studyScore.bean.front.updateScoreTypeVo;
import com.yxy.service_studyScore.bean.front.updateVo;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.*;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("service_studyScore/updateInit")
@RestController
@CrossOrigin
public class updateInit {
    @Autowired
    ServiceLessonService lessonService;

    @Autowired
    ServiceLessonattributeService lessonattributeService;

    @Autowired
    ServiceFormulaService formulaService;
    @Autowired
    ServiceScoretypeService scoretypeService;


    @PostMapping("updateFormula")////修改公式//直接修改 不用判断
    public ResultUtils updateFormula(@RequestBody updateVo vo){
        ServiceFormula formulaBean=null;


            if (formulaService.isWrongAttribute(vo.getNewValue(),vo.getSchoolName(),vo.getDepartment())){

                 formulaBean = formulaService.updateFormula(vo.getSchoolName(),vo.getDepartment(),vo.getChangedItem(),vo.getNewValue());
                 System.out.println("修改");
                return ResultUtils.OK().data("新公式",formulaBean).code(200);

            }

            return ResultUtils.error().message("公式插入错误").code(400);

    };

    @PostMapping("updateAttribute")
    public ResultUtils updateAttribute(@RequestBody updateVo vo){

       if( lessonattributeService.updateAtrribute(vo.getSchoolName(),vo.getDepartment(),vo.getChangedItem(),vo.getNewValue())){
           try {
               return ResultUtils.OK().data("新课程属性",lessonattributeService.getAttributeByAdmin(vo.getSchoolName(),vo.getDepartment())).code(200);
           } catch (zongceException e) {
               e.printStackTrace();
           }
       }
        return ResultUtils.error().message("修改课程属性失败").code(400);

    }

    @PostMapping("updateScopeType")
    public ResultUtils updateScopeType(@RequestBody updateScoreTypeVo vo){
           if(scoretypeService.updateScoretype(vo.getSchoolName(),vo.getDepartment(),vo.getChangedItem(),vo.getChangedValue(),vo.getNewItem(),vo.getNewValue()))
           {
               try {
                   return ResultUtils.OK().data("新综测分类属性",scoretypeService.getScopeTypeByAdmin(vo.getSchoolName(),vo.getDepartment())).code(200);
               } catch (zongceException e) {
                   e.printStackTrace();
               }
           }
        return ResultUtils.error().message("修改综测分失败").code(400);

    }



    @PostMapping("updateLesson")

    public ResultUtils updateLesson(@RequestBody  updateVo vo){
        if(lessonService.updateLesson(vo.getSchoolName(),vo.getDepartment(),vo.getChangedItem(),vo.getNewValue())){
            return ResultUtils.OK().data("新课程",lessonService.getLessonByAdmin(vo.getSchoolName(),vo.getDepartment())).code(200);

        }
        return ResultUtils.error().message("修改课程失败").code(400);

    }

}
