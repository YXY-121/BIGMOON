package com.yxy.service_self.controller;


import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscore;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscore1;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscoreto;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscoreto1;
import com.yxy.service_self.service.ServiceSelftotalscoreService;
import com.yxy.serviceBase.resultCode.ResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自测总成绩表 前端控制器
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/service_self/selfTestTotalScore")
public class ServiceSelftotalscoreController {
    //自测没有修改功能只能看，因为系统自动计算好的
    //班干或者普通学生看自己
    //管理员也可以用来特地查看某个同学
    @Autowired
    ServiceSelftotalscoreService serviceSelftotalscoreService;
    @ApiOperation("自测给普通学生用的或班干（主要看前端按钮）")//选择是否年份查询自己，这个时候传的学号学校班级专业院系都是自己的
    @PostMapping("/selectForStudent")//班干页面还可以选择学号查看别人
    public ResultUtils selectSelftotalscore(@ApiParam(name = "VoServiceSelftotalscore", value = "普通学生选择是否按年份查看", required = true) @RequestBody VoServiceSelftotalscore voServiceSelftotalscore){
       if(voServiceSelftotalscore.equals("all")==false){
           List<VoServiceSelftotalscoreto1> voServiceSelftotalscoreto1=new ArrayList<>();
           voServiceSelftotalscoreto1=serviceSelftotalscoreService.selectSelftotalscore1(voServiceSelftotalscore);
           if(voServiceSelftotalscoreto1.size()!=0){
               return ResultUtils.OK().data("普通学生某个年份自测的查询",voServiceSelftotalscoreto1);
           }else{
               return ResultUtils.error().message("此年份查无数据重新选择年份");
           }
       }else{
           //查询全部输出年份
           List<VoServiceSelftotalscoreto> voServiceSelftotalscoreto=new ArrayList<>();
           voServiceSelftotalscoreto=serviceSelftotalscoreService.selectSelftotalscore(voServiceSelftotalscore);
           if(voServiceSelftotalscoreto.size()!=0){
               return ResultUtils.OK().data("普通学生全部自测的查询",voServiceSelftotalscoreto);
           }else{
               return ResultUtils.error().message("查无数据");
           }
       }
    }
    //班干看自己班的同学（某个年份）不选择年份自测成绩排名没有意义
    @ApiOperation("专门给班干查看某一年班里所有同学的自测成绩（主要看前端按钮）")//专门给班干查看某一年班里所有同学的自测成绩
    @PostMapping("/selectClass")//班干页面还可以选择学号查看别人
    public ResultUtils selectSelftotalscore1(@ApiParam(name = "VoServiceSelftotalscore1", value = "班干查看某一年班里所有同学的自测成绩", required = true) @RequestBody VoServiceSelftotalscore1 VoServiceSelftotalscore1){
       List<VoServiceSelftotalscoreto1> VoServiceSelftotalscoreto1=serviceSelftotalscoreService.selectSelftotalscore2(VoServiceSelftotalscore1);
       if(VoServiceSelftotalscoreto1.size()!=0){
           return ResultUtils.OK().data("班里所有同学在这一年的自测成绩",VoServiceSelftotalscoreto1);
       }else{
          return ResultUtils.error().message("暂无数据");
       }
    }
    //管理员查看某个院系某个专业某个年份自测（不选择年份自测成绩排名没有意义）
    @ApiOperation("管理员查看某个院系某个专业某个年份自测（主要看前端按钮）")//理员查看某个院系某个专业某个年份自测
    @PostMapping("/selectMajor")//班干页面还可以选择学号查看别人
    public ResultUtils selectSelftotalscore3(@ApiParam(name = "VoServiceSelftotalscore2", value = "管理员查看某个院系某个专业某个年份自测", required = true) @RequestBody VoServiceSelftotalscore1 VoServiceSelftotalscore2){
        List<VoServiceSelftotalscoreto1> VoServiceSelftotalscoreto1=serviceSelftotalscoreService.selectSelftotalscore3(VoServiceSelftotalscore2);
        if(VoServiceSelftotalscoreto1.size()!=0){
            return ResultUtils.OK().data("某个专业所有同学在这一年的自测成绩",VoServiceSelftotalscoreto1);
        }else{
            return ResultUtils.error().message("暂无数据");
        }
    }
}

