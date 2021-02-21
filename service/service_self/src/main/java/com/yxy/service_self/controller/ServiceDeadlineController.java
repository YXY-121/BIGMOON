package com.yxy.service_self.controller;


import com.yxy.service_self.bean.vo.voServiceDeadline.VoServiceDeadline;
import com.yxy.service_self.bean.vo.voServiceDeadline.VoServiceDeadlineto;
import com.yxy.service_self.service.ServiceDeadlineService;
import com.yxy.serviceBase.resultCode.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自测截止审核时间表 前端控制器
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/service_self/setDeadline")
@Api("自测截止审核截止时间表")
public class ServiceDeadlineController {
//插入在那边的加分大类controller初始化就写了
    //管理员和班干用的
    @Autowired
    ServiceDeadlineService serviceDeadlineService;
    @PostMapping("/selectDeadline")//查询某个学校某个院系
    public ResultUtils selectDeadline(@ApiParam(name = "VoServiceDeadline对象", value = "自测截止审核时间查询", required = true) @RequestBody VoServiceDeadline voServiceDeadline){
        List<VoServiceDeadlineto> deadlineto=new ArrayList<>();
        deadlineto= serviceDeadlineService.selectDeadline(voServiceDeadline);
        if(deadlineto.size()!=0){
            return ResultUtils.OK().data("自测截止审核截止时间",deadlineto);

        }else{
            return ResultUtils.error().message("暂无数据");
        }
    }
   /* @PostMapping("/updateDeadline")
    public ResultUtils updateDeadline(@ApiParam(name = "VoServiceDeadlineto对象", value = "自测截止审核时间修改", required = true) @RequestBody VoServiceDeadlineto voServiceDeadlineto){
        int i=serviceDeadlineService.updateDeadline(voServiceDeadlineto);
        if(i>0){
            return ResultUtils.OK().message("修改成功！");
        }else{
            return ResultUtils.error().message("修改失败请输入正确的学校专业年份数据重新操作");
        }
    }*/

}

