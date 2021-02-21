package com.yxy.service_userCenter.controller.front;


import com.yxy.service_userCenter.bean.ServiceUser;
import com.yxy.service_userCenter.bean.excel.excelVo;
import com.yxy.service_userCenter.bean.front.userMegVo;
import com.yxy.service_userCenter.service.ServiceUserService;
import com.zongce.serviceBase.exception.zongceException;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("service_userCenter/studentMessage")
public class studentMessage {
    @Autowired
    ServiceUserService userService;
//搞定了

   @PostMapping("updateSelfPwd")
    public ResultUtils updateSelfPwd(@RequestBody userMegVo vo){
            //xuexiao id new pwd
       String schoolName=vo.getSchoolName();
       String UserId=vo.getUserId();
        boolean isExist=userService.isExistStudent(schoolName,UserId);
        if(isExist){
            boolean checkCode = userService.checkCode(userService.getPhone(schoolName,UserId), vo.getCode());
            if(checkCode){
                boolean flag= userService.updateSelfPwd(schoolName,UserId,vo.getNewPwd());
                return ResultUtils.OK().message("修改密码成功");
            }
            return ResultUtils.error().message("验证码错误或者超时");


        }
        return ResultUtils.error().message("该学生不存在");
    }
    //搞定了

    @PostMapping("sendMsm")
    public ResultUtils sendMsm(String phone){

        boolean b = userService.sendCode(phone);
        if(b)
            return ResultUtils.OK().message("验证码已发送");
            return ResultUtils.error().message("验证码发送失败");
    }
//搞定了
    @PostMapping("uploadStudentMessage")
    public ResultUtils uploadStudentMessage(String schoolName,String pref,MultipartFile file){

            userService.uploadUserFile(schoolName, pref, file);
        return ResultUtils.OK().message("设置课程成功");


    }
   /* @PostMapping("updateUserMeg")
    public ResultUtils updateUserMeg(@RequestBody updateVo vo){
        if(userService.updateUserMeg(vo.getSchoolName(),vo.getDepartment(),vo.getChangedItem(),vo.getNewValue())){
            return ResultUtils.OK().data("新用户",userService.getUserMegByAdmin(vo.getSchoolName(),vo.getDepartment())).code(200);
        }
        return ResultUtils.error().message("修改用户失败").code(400);

    }*/

    @PostMapping("selectUserMeg")
    public ResultUtils selectUserMeg(@RequestBody ServiceUser admin)  {

        //查看课程属性设置
        List<ServiceUser> a= userService.getUserMegByAdmin(admin.getSchoolName(),admin.getDepartment());
        if(a.isEmpty()){
            try {
                throw new zongceException("查无学生信息");
            } catch (zongceException e) {
                e.printStackTrace();
                return ResultUtils.error().message("查无该院校学生信息");
            }
        }
        return ResultUtils.OK().data("UserMeg",a);
    }

 /*   public ResultUtils updateStudentPwdByAdmin(@Autowired userMegVo vo){//需要有权限才可以做

    }

    public ResultUtils findStudentPwdByAdmin(@Autowired userMegVo vo){//管理员帮忙找回密码 select

    }
    public ResultUtils deleteStudentMegByAdmin(@Autowired userMegVo vo){//需要有权限才可以做

    }

    public ResultUtils updateStudentMeg(){
    }*/

}
