package com.yxy.service_self.controller;


import com.yxy.serviceBase.exception.ServiceException;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoServiceTotalscore;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoServiceTotalscoreto;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoUpdatetatolscore;
import com.yxy.service_self.service.ServiceTotalscoreService;
import com.yxy.serviceBase.resultCode.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 总成绩表 前端控制器
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/service_self/totalScore")
@Api("总表")
public class ServiceTotalscoreController {
    @Autowired
    ServiceTotalscoreService serviceTotalscoreService;
    @ApiOperation("总表给班干用的")//给班干查询班里所有同学某一年总成绩总的或者各种审核状态的
    @PostMapping("/selectClassTotalScore")
    public ResultUtils selectTotalall(@ApiParam(name = "voServiceTotalscore", value = "总表按年份查看自己综测总成绩按审核性查询各个年份成绩和审核性传all查看所有年份传all", required = true) @RequestBody VoServiceTotalscore voServiceTotalscore){
        List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
        voServiceTotalscoreto=serviceTotalscoreService.selectTotalAll(voServiceTotalscore);
        if(voServiceTotalscoreto.size()!=0){
            return ResultUtils.OK().data("成绩的List",voServiceTotalscoreto);
        }else{
           return ResultUtils.error().message("暂无数据！") ;
        }
    }

    @ApiOperation("总表给普通学生用的")//按年份查询自己，这个时候传的学号学校班级都是自己的
    @PostMapping("/selectStudentTotalScore")
    public ResultUtils selectTotalallstudent(@ApiParam(name = "voServiceTotalscore", value = "普通学生按年份查看自己综测总成绩按审核性查询各个年份成绩和审核性传all查看所有年份传all", required = true) @RequestBody VoServiceTotalscore voServiceTotalscore){
        List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
        voServiceTotalscoreto=serviceTotalscoreService.selectTotalSelf(voServiceTotalscore);
        if(voServiceTotalscoreto.size()!=0){
            return ResultUtils.OK().data("普通学生成绩的List",voServiceTotalscoreto);
        }else{
            return ResultUtils.error().message("暂无数据！") ;
        }
    }
    @ApiOperation("总表给管理员用的")//学校班级（一定要有），因为不同学校不比较），按年份审核性
    @PostMapping("/selectClassTotalScoreByAdmin")//按同个学校每个班级
    public ResultUtils selectTotalallsuper1(@ApiParam(name = "voServiceTotalscore", value = "管理员查询", required = true) @RequestBody VoServiceTotalscore voServiceTotalscore){
        List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
        voServiceTotalscoreto=serviceTotalscoreService.selectTotalSuper1(voServiceTotalscore);
        if(voServiceTotalscoreto.size()!=0){
            return ResultUtils.OK().data("管理员按学校专业成绩的List",voServiceTotalscoreto);
        }else{
            return ResultUtils.error().message("暂无数据！") ;
        }
    }
    @ApiOperation("总表给管理员用的")//学校专业（一定要有），因为不同学校不比较），按年份审核性
    @PostMapping("/selectMajorTotalScore")//按同个学校每个专业
    public ResultUtils selectTotalallsuper2(@ApiParam(name = "voServiceTotalscore", value = "管理员查询", required = true) @RequestBody VoServiceTotalscore voServiceTotalscore){
        List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
        voServiceTotalscoreto=serviceTotalscoreService.selectTotalSuper2(voServiceTotalscore);
        if(voServiceTotalscoreto.size()!=0){
            return ResultUtils.OK().data("管理员按学校专业成绩的List",voServiceTotalscoreto);
        }else{
            return ResultUtils.error().message("暂无数据！") ;
        }
    }
    @ApiOperation("总表给班长/管理员用的")//修改某个同学//管理员也可以用
    @PostMapping("/updateTotalScore")//
    public ResultUtils updateTotal(@ApiParam(name = "voUpdatetatolscore", value = "总表给班长用的修改某个同学", required = true) @RequestBody VoUpdatetatolscore voUpdatetatolscore){
        try{
            serviceTotalscoreService.updateTotal(voUpdatetatolscore);
            return ResultUtils.OK().message("修改成功");
        } catch (ServiceException e){
            e.printStackTrace();
            return ResultUtils.error().code(e.getCode()).message(e.getMessage());
        }
    }
}

