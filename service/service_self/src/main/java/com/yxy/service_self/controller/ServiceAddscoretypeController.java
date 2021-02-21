package com.yxy.service_self.controller;


import com.yxy.serviceBase.exception.ServiceException;
import com.yxy.service_self.bean.vo.voAddscoretype.*;
import com.yxy.service_self.service.ServiceAddscoretypeService;
import com.yxy.serviceBase.resultCode.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 自测加分大类表 前端控制器
 * </p>
 *
 * @author wlh
 * @since 2020-12-13
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/service_self/setInit")
@Api("自测初始化模块")
public class ServiceAddscoretypeController {
    @Autowired
    ServiceAddscoretypeService serviceAddscoretypeService;
    @ApiOperation("自测初始化模块添加")
    @PostMapping("/addInitByClassLeader")
    public ResultUtils ServiceAddscoretype(@ApiParam(name = "voAddscoretype", value = "自测初始化", required = true) @RequestBody List<VoAddscoretype> voAddscoretype){
        try{
            serviceAddscoretypeService.saveadd(voAddscoretype);
            return ResultUtils.OK().message("初始化成功") ;
        }catch (ServiceException e){
            e.printStackTrace();
            return ResultUtils.error().code(e.getCode()).message(e.getMessage());
        }
    }
    @ApiOperation("自测初始化模块添加")//给管理员用
    @PostMapping("/addInitByAdmin")
    public ResultUtils ServiceAddscoretypeadmin(@ApiParam(name = "voAddscoretype1", value = "自测初始化", required = true) @RequestBody List<VoAddscoretype1> voAddscoretype1){
       try{
           serviceAddscoretypeService.saveadd1(voAddscoretype1);
           return ResultUtils.OK().message("初始化成功") ;
       }catch (ServiceException e){
           e.printStackTrace();
           return ResultUtils.error().code(e.getCode()).message(e.getMessage());
       }
    }
    @ApiOperation("查询某个学校有哪些加分大类") //查询某个学校某个院某一年或者某个学校某个院有哪些加分类
    @PostMapping("/selectSelfTestScoreType")//给管理员使用（管理员用来查看的可以看某个学校某个专业全部年份）
    public ResultUtils ServiceAddscoretypeSelect(@ApiParam(name = "VoselectAddscoretypeselect1", value = "自测加分大类查询辅助类", required = true) @RequestBody VoselectAddscoretypeselect1 voselectAddscoretypeselect1){
      if(voselectAddscoretypeselect1.equals("all")==false){
          List<VoselectAddscoretypeselectto> voselectAddscoretypeselectto=serviceAddscoretypeService.ServiceAddscoretypeSelect(voselectAddscoretypeselect1);
          if(voselectAddscoretypeselectto.size()!=0){
              return ResultUtils.OK().data("voselectAddscoretypeselectto",voselectAddscoretypeselectto);
          }else{
              return ResultUtils.error().message("暂无数据");
          }

      }else{
          List<VoselectAddscoretypeselectto1> voselectAddscoretypeselectto1=serviceAddscoretypeService.ServiceAddscoretypeSelect1(voselectAddscoretypeselect1);
         if(voselectAddscoretypeselectto1.size()!=0){
             return ResultUtils.OK().data("voselectAddscoretypeselectto1",voselectAddscoretypeselectto1);
         }else{
             return ResultUtils.error().message("暂无数据");
         }
      }
    }
    @ApiOperation("查询某个学校某个院系有哪些加分大类") //给学生进行分数类型和年份的自测模块选择并不是真的为了查询出来给学生看
    @PostMapping("/studentSelectSelfTestScoreType")//查询出分数类型和年份
    public ResultUtils ServiceAddscoretypeSelectself(@ApiParam(name = "VoselectAddscoretypeselect", value = "自测加分大类查询辅助类", required = true) @RequestBody VoselectAddscoretypeselect voselectAddscoretypeselect) {
    List<VoselectAddscoretypeselectto3> VoselectAddscoretypeselectto3=serviceAddscoretypeService.ServiceAddscoretypeSelectself(voselectAddscoretypeselect);
    if(VoselectAddscoretypeselectto3.size()!=0){
        return ResultUtils.OK().data("学生的加分大类选择List",VoselectAddscoretypeselectto3);
    }else{
        return ResultUtils.error().message("查无数据此学校院系还没自测模块初始化");
        //如果初始化了肯定有结果
    }
    }

    //下面开始写修改,学校和学院不能改因为已经是固定的，如果想改就直接删除在初始化
    @ApiOperation("修改初始化大类")
    @PostMapping("/updateInit")
    public ResultUtils ServiceAddscoretypeupdate(@ApiParam(name = "VoAddscoretypeupdate", value = "修改初始化辅助类", required = true) @RequestBody VoAddscoretypeupdate voAddscoretypeupdate) {
         try{
             serviceAddscoretypeService.ServiceAddscoretypeupdate(voAddscoretypeupdate);
             return ResultUtils.OK().message("修改成功！");
         }catch (ServiceException e){
             e.printStackTrace();
             return ResultUtils.error().code(e.getCode()).message(e.getMessage());
         }
    }

    //删除函数
    @ApiOperation("删除初始化大类")//删除是删除所有的（删除自测时间加分大类跟着全都删除或者加分大类某个学校某一年某个专业某一条也都删除再去重新初始化）
    @PostMapping("/deleteInit")
    public ResultUtils ServiceAddscoretypedelete(@ApiParam(name = "VoAddscoretypedelete", value = "删除初始化辅助类", required = true) @RequestBody VoAddscoretypedelete VoAddscoretypedelete){
        try{
            serviceAddscoretypeService.ServiceAddscoretypedelete(VoAddscoretypedelete);
            return ResultUtils.OK().message("删除数据成功");
        }catch (ServiceException e){
            e.printStackTrace();
            return ResultUtils.error().code(e.getCode()).message(e.getMessage());
        }
    }
}

