package com.yxy.service_self.controller;


import com.yxy.serviceBase.exception.ServiceException;
import com.yxy.service_self.bean.vo.voServiceSelftestmodule.*;
import com.yxy.serviceBase.resultCode.ResultUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 自测模块表 前端控制器
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/service_self/selfTest")
public class ServiceSelftestmoduleController {
    @Autowired
    com.yxy.service_self.service.ServiceSelftestmoduleService ServiceSelftestmoduleService;
//封开装，烦死了
    @ApiOperation("自测模块添加")//传数据对象
    @PostMapping(value ="/selfTestAdd")
    public ResultUtils ServiceSelftestmoduleadd(@ApiParam(name = "voServiceSelftestmodule", value = "自测模块", required = true) @RequestBody List<voServiceSelftestmodule> voServiceSelftestmodule){
        try{
          List<voServiceSelftestmoduleto> voServiceSelftestmoduleto=ServiceSelftestmoduleService.saveadd(voServiceSelftestmodule);
           return ResultUtils.OK().message("添加成功").data("List",voServiceSelftestmoduleto) ;
       }catch (ServiceException serviceException){
          return  ResultUtils.error().message(serviceException.getMessage());
       }
    }
    @ApiOperation("自测模块删除")//删除
    @PostMapping(value ="/selfTestDelete")
    public ResultUtils ServiceSelftestmoduledelete(@ApiParam(name = "voServiceSelftestmoduledelete", value = "自测模块删除", required = true) @RequestBody List<voServiceSelftestmoduledelete> voServiceSelftestmoduledelete){
        try{
            ServiceSelftestmoduleService.deleteself(voServiceSelftestmoduledelete);
            return ResultUtils.OK().message("删除成功！");
        }catch (ServiceException serviceException){
            return  ResultUtils.error().message(serviceException.getMessage());
        }
    }
    @ApiOperation("自测模块更新")//更新（前端那边先执行删除文件接口在执行上传接口再把文件上传过来）
    @PostMapping(value ="/selfTestUpdate")
    public ResultUtils ServiceSelftestmoduleupdate(@ApiParam(name = "voServiceSelftestmoduleupdate", value = "自测模块更新", required = true) @RequestBody List<voServiceSelftestmoduleupdate> voServiceSelftestmoduleupdate){
        try{
            ServiceSelftestmoduleService.updateself(voServiceSelftestmoduleupdate);
            return ResultUtils.OK().message("更新成功！");
        }catch (ServiceException serviceException){
            return  ResultUtils.error().message(serviceException.getMessage());
        }
    }
    @ApiOperation("自测模块查询")//普通学生或者班长按分数类型年份查看（学校学号也要传，分数类型和年份前端选择, 年份选择all就把分数类型的每一年加分一起查出来）
    @PostMapping(value ="/selectSelfTest")
    public ResultUtils ServiceSelftestmoduleselect(@ApiParam(name = "voServiceSelftestmoduleselect", value = "自测模块查询（查看自己）", required = true) @RequestBody voServiceSelftestmoduleselect voServiceSelftestmoduleselect){
            //需要返回年份的
            if(voServiceSelftestmoduleselect.getYear().equals("all")){
            List<voServiceSelftestmoduleto1> voServiceSelftestmoduleto1 = ServiceSelftestmoduleService.selectself(voServiceSelftestmoduleselect);
           if(voServiceSelftestmoduleto1.size()!=0){
               return ResultUtils.OK().data("voServiceSelftestmoduleto1",voServiceSelftestmoduleto1);
           }else{
               return ResultUtils.error().message("暂无数据！");
           }
            }else{
                //特定某年不需要返回年份
                List<voServiceSelftestmoduleto2> voServiceSelftestmoduleto2=ServiceSelftestmoduleService.selectself1(voServiceSelftestmoduleselect);
                if(voServiceSelftestmoduleto2.size()!=0){
                    return ResultUtils.OK().data("voServiceSelftestmoduleto2",voServiceSelftestmoduleto2);
                }else{
                    return ResultUtils.error().message("暂无数据！");
                }
            }
    }
    @ApiOperation("自测模块查询班级")//班长按分数类型年份查看自己班（学校班级学院也要传，分数类型和年份前端选择, 年份一定要选不然如果所有年份一起很混乱）
    @PostMapping(value ="/selectClassSelfTest")
    public ResultUtils ServiceSelftestmoduleselect1(@ApiParam(name = "voServiceSelftestmoduleselect1对象", value = "自测模块查询班级", required = true) @RequestBody voServiceSelftestmoduleselect1 voServiceSelftestmoduleselect1){
        //不需要返回年份的
            List<voServiceSelftestmoduleto2> voServiceSelftestmoduleto2 = ServiceSelftestmoduleService.selectself2(voServiceSelftestmoduleselect1);
          if(voServiceSelftestmoduleto2.size()!=0){
              return ResultUtils.OK().data("voServiceSelftestmoduleto2",voServiceSelftestmoduleto2);
          }else {
              return ResultUtils.error().message("暂无数据！");
          }

    }
    @ApiOperation("自测模块查询专业")//管理员按分数类型年份查看（学校专业学院也要传，分数类型和年份前端选择, 年份一定要选不然如果所有年份一起很混乱）
    @PostMapping(value ="/selectMajorSelfTest")
    public ResultUtils ServiceSelftestmoduleselect2(@ApiParam(name = "voServiceSelftestmoduleselect2对象", value = "自测模块查询专业", required = true) @RequestBody voServiceSelftestmoduleselect2 voServiceSelftestmoduleselect2){
        //不需要返回年份的
        List<voServiceSelftestmoduleto2> voServiceSelftestmoduleto2 = ServiceSelftestmoduleService.selectself3(voServiceSelftestmoduleselect2);
        if(voServiceSelftestmoduleto2.size()!=0){
            return ResultUtils.OK().data("voServiceSelftestmoduleto2",voServiceSelftestmoduleto2);
        }else {
            return ResultUtils.error().message("暂无数据！");
        }

    }



    @ApiOperation("自测模块添加用来上传文件的")//多个文件上传（阿里云）
    @PostMapping(value ="/uploadFiles",headers = "content-type=multipart/form-data")////这里的content-type的媒体类型为：multipart/form-data ，表示表单中进行文件上传。个人感觉是由于参数中的MultipartFile类型影响了requestbody本应所对应的content-type: application/json,感觉可能是MultipartFile的媒体类型优先级高，会覆盖application/json。
    public ResultUtils ServiceSelftestmoduleupload(@ApiParam(name = "files", value = "自测模块文件", required = true)@RequestParam("files")  MultipartFile[] files){
      if(files!=null||files.length>0){
          try{
              List<String> urls=ServiceSelftestmoduleService.upload(files);
              return ResultUtils.OK().message("添加成功").data("urls",urls) ;
          }catch (ServiceException serviceException){
              return  ResultUtils.error().message(serviceException.getMessage());
          }
      }else {
          return  ResultUtils.error().message("请不要上传空文件");
      }

    }
    @ApiOperation("自测模块添加用来删除文件的")//多个文件删除（阿里云那里）
    @PostMapping(value ="/deleteFiles")////这里的content-type的媒体类型为：multipart/form-data ，表示表单中进行文件上传。个人感觉是由于参数中的MultipartFile类型影响了requestbody本应所对应的content-type: application/json,感觉可能是MultipartFile的媒体类型优先级高，会覆盖application/json。
    public ResultUtils ServiceSelftestmoduledeletefiles(@ApiParam(name = "urls", value = "自测模块文路径", required = true)  List<String> urls){
        try{
            ServiceSelftestmoduleService.deletefiles(urls);
            return ResultUtils.OK().message("删除成功") ;
        }catch (ServiceException serviceException){
            return  ResultUtils.error().message(serviceException.getMessage());
        }
    }


}

