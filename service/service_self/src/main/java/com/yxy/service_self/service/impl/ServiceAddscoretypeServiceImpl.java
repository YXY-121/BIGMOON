package com.yxy.service_self.service.impl;

import org.springframework.beans.BeanUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_self.bean.ServiceAddscoretype;
import com.yxy.service_self.bean.ServiceDeadline;
import com.yxy.service_self.bean.ServiceSelftotalscore;
import com.yxy.service_self.bean.ServiceTotalscore;
import com.yxy.service_self.bean.vo.voAddscoretype.*;
import com.yxy.service_self.mapper.ServiceAddscoretypeMapper;
import com.yxy.service_self.mapper.ServiceDeadlineMapper;
import com.yxy.service_self.mapper.ServiceSelftotalscoreMapper;
import com.yxy.service_self.mapper.ServiceTotalscoreMapper;
import com.yxy.service_self.service.ServiceAddscoretypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.service_self.service.ServiceTotalscoreService;


import com.yxy.serviceBase.exception.ServiceException;

import com.yxy.service_userCenter.bean.vo.VoServiceUser1;
import com.yxy.service_userCenter.bean.vo.VoServiceUser2;
import com.yxy.service_userCenter.bean.vo.VoServiceUser5;

import com.yxy.service_userCenter.provider.ServiceproviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 自测加分大类表 服务实现类
 * </p>
 *
 * @author wlh
 * @since 2020-12-13
 */
@Service
public class ServiceAddscoretypeServiceImpl extends ServiceImpl<ServiceAddscoretypeMapper, ServiceAddscoretype> implements ServiceAddscoretypeService {

   @Autowired
   ServiceDeadlineMapper ServiceDeadlineMapper;
   @Autowired
   com.yxy.service_self.service.ServiceDeadlineService ServiceDeadlineService;
   @Autowired
   ServiceSelftotalscoreMapper ServiceSelftotalscoreMapper;
   @Autowired
   com.yxy.service_self.service.ServiceSelftotalscoreService ServiceSelftotalscoreService;
   @Autowired
   ServiceTotalscoreMapper ServiceTotalscoreMapper;
   @Autowired
   ServiceTotalscoreService ServiceTotalscoreService;
    @Reference(parameters = {"protocol","userCenterhessian"})
     ServiceproviderService ServiceproviderService;

    @Transactional(rollbackFor = Exception.class)
   @Override//给班干的
    public void saveadd(List<VoAddscoretype> voAddscoretype)  {
       ServiceDeadline ServiceDeadline=new ServiceDeadline();
       //自测时间截止表，一次就好
       String  studentDeadline=voAddscoretype.get(0).getStudentDeadline();
       String  managerDeadline=voAddscoretype.get(0).getManagerDeadline();
       Date studentDeadline1=null;
       Date managerDeadline1=null;
       try{
           studentDeadline1=new SimpleDateFormat("yyy-MM-dd").parse(studentDeadline);
           managerDeadline1=new SimpleDateFormat("yyyy-MM-dd").parse(managerDeadline);
       }catch(ParseException e){
           e.printStackTrace();
           System.out.println("日期转换异常");
       }
        BeanUtils.copyProperties(voAddscoretype.get(0),ServiceDeadline);
       ServiceDeadline.setStudentDeadline(studentDeadline1);
       ServiceDeadline.setManagerDeadline(managerDeadline1);
       //捕捉异常
       try{
           QueryWrapper<ServiceDeadline> queryWrapper = new QueryWrapper<>();
           Map<String, Object> map = new HashMap<>();
           map.put("schoolName", voAddscoretype.get(0).getSchoolName());
           map.put("department",voAddscoretype.get(0).getDepartment());
           map.put("year", voAddscoretype.get(0).getYear());
           queryWrapper.allEq(map);
           List<ServiceDeadline> ServiceDeadline1=ServiceDeadlineMapper.selectList(queryWrapper);
           if(ServiceDeadline1.size()==0){
               ServiceDeadlineMapper.insert(ServiceDeadline);//主要捕捉它，它插不入下面肯定插不入
               //插自测加分大类表,上面已经判断年份不一样了所下面不会插入重复的所以不用捕捉异常
           }
           for(int g=0;g<voAddscoretype.size();g++){
               //加分大类表
               //为了生成不同对象放里面
               ServiceAddscoretype serviceAddscoretype=new ServiceAddscoretype();
               BeanUtils.copyProperties(voAddscoretype.get(g),serviceAddscoretype);
               baseMapper.insert(serviceAddscoretype);
           }
           //总表自测表插一次就好，不用不做理由同上
           List<VoServiceUser1> VoServiceUser1 =new ArrayList<>();
           VoServiceUser2 VoServiceUser2=new VoServiceUser2();
           ServiceTotalscore serviceTotalscore=new ServiceTotalscore();
           ServiceSelftotalscore serviceSelftotalscore=new ServiceSelftotalscore();
           BeanUtils.copyProperties(voAddscoretype.get(0),VoServiceUser2);
           VoServiceUser1=ServiceproviderService.selectUser1(VoServiceUser2);
           for(int k=0;k<VoServiceUser1.size();k++){
               BeanUtils.copyProperties(VoServiceUser1.get(k),serviceTotalscore);
               BeanUtils.copyProperties(VoServiceUser1.get(k),serviceSelftotalscore);
               serviceTotalscore.setYear(voAddscoretype.get(0).getYear());
               serviceSelftotalscore.setYear(voAddscoretype.get(0).getYear());
               ServiceTotalscoreMapper.insert(serviceTotalscore);
               ServiceSelftotalscoreMapper.insert(serviceSelftotalscore);
           }
       }catch(Exception e){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           throw new ServiceException(100,"已有此学校自测时间数据初始化失败请不要重复操作");
       }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override//管理员用的
    public void saveadd1(List<VoAddscoretype1> voAddscoretype1) {
        ServiceDeadline ServiceDeadline=new ServiceDeadline();
        //自测时间截止表，一次就好
        String  studentDeadline=voAddscoretype1.get(0).getStudentDeadline();
        String  managerDeadline=voAddscoretype1.get(0).getManagerDeadline();
        Date studentDeadline1=null;
        Date managerDeadline1=null;
        try{
            studentDeadline1=new SimpleDateFormat("yyy-MM-dd").parse(studentDeadline);
            managerDeadline1=new SimpleDateFormat("yyyy-MM-dd").parse(managerDeadline);
        }catch(ParseException e){
            e.printStackTrace();
            System.out.println("日期转换异常");
        }
        BeanUtils.copyProperties(voAddscoretype1.get(0),ServiceDeadline);
        ServiceDeadline.setStudentDeadline(studentDeadline1);
        ServiceDeadline.setManagerDeadline(managerDeadline1);
      //  try{
        QueryWrapper<ServiceDeadline> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voAddscoretype1.get(0).getSchoolName());
        map.put("department",voAddscoretype1.get(0).getDepartment());
        map.put("year", voAddscoretype1.get(0).getYear());
        queryWrapper.allEq(map);
        List<ServiceDeadline> ServiceDeadline1=ServiceDeadlineMapper.selectList(queryWrapper);
        if(ServiceDeadline1.size()==0){
            ServiceDeadlineMapper.insert(ServiceDeadline);//主要捕捉它，它插不入下面肯定插不入
            //插自测加分大类表,上面已经判断年份不一样了所下面不会插入重复的所以不用捕捉异常
        }
            for(int g=0;g<voAddscoretype1.size();g++){
                //加分大类表
                //为了生成不同ID放里面
                ServiceAddscoretype serviceAddscoretype=new ServiceAddscoretype();
                BeanUtils.copyProperties(voAddscoretype1.get(g),serviceAddscoretype);
                baseMapper.insert(serviceAddscoretype);
           }
        QueryWrapper<ServiceTotalscore> queryWrapper1 = new QueryWrapper<>();
        Map<String, Object> map1 = new HashMap<>();
        QueryWrapper<ServiceSelftotalscore> queryWrapper2 = new QueryWrapper<>();
        Map<String, Object> map2 = new HashMap<>();
            //总表自测插一次就好, 不用捕捉理由同上
            List<VoServiceUser1> VoServiceUser1 =new ArrayList<>();
            VoServiceUser5 VoServiceUser5=new VoServiceUser5();
            ServiceTotalscore serviceTotalscore=new ServiceTotalscore();
            ServiceSelftotalscore serviceSelftotalscore=new ServiceSelftotalscore();
            BeanUtils.copyProperties(voAddscoretype1.get(0),VoServiceUser5);
            VoServiceUser1=ServiceproviderService.selectUser2(VoServiceUser5);
            for(int k=0;k<VoServiceUser1.size();k++){
                BeanUtils.copyProperties(VoServiceUser1.get(k),serviceTotalscore);
                BeanUtils.copyProperties(VoServiceUser1.get(k),serviceSelftotalscore);
                serviceTotalscore.setYear(voAddscoretype1.get(0).getYear());
                serviceSelftotalscore.setYear(voAddscoretype1.get(0).getYear());
              //总表
                map1.put("schoolName", serviceTotalscore.getSchoolName());
                map1.put("userId",serviceTotalscore.getUserId());
                map1.put("year", serviceTotalscore.getYear());
                queryWrapper1.allEq(map1);
                List<ServiceTotalscore> serviceTotalscore1=ServiceTotalscoreMapper.selectList(queryWrapper1);
                if(serviceTotalscore1.size()==0){
                    ServiceTotalscoreMapper.insert(serviceTotalscore);
                }
                //自测总表
                map2.put("schoolName", serviceSelftotalscore.getSchoolName());
                map2.put("userId",serviceSelftotalscore.getUserId());
                map2.put("year", serviceSelftotalscore.getYear());
                queryWrapper2.allEq(map1);
                List<ServiceSelftotalscore> serviceSelftotalscore1=ServiceSelftotalscoreMapper.selectList(queryWrapper2);
                if(serviceSelftotalscore1.size()==0){
                    ServiceSelftotalscoreMapper.insert(serviceSelftotalscore);
                }
            }
       // }catch (Exception e){
        //   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      //     throw new ServiceException(100,"已有此自测时间数据初始化失败请重新操作");
      //  }
    }

    @Override//不需要返回年份
    public List<VoselectAddscoretypeselectto> ServiceAddscoretypeSelect(VoselectAddscoretypeselect1 voselectAddscoretypeselect1) {
        QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voselectAddscoretypeselect1.getSchoolName());
        map.put("department",voselectAddscoretypeselect1.getDepartment());
        map.put("year", voselectAddscoretypeselect1.getYear());
        queryWrapper.allEq(map);
        queryWrapper.select("schoolName","department","selfTestScoreType","proportion");
        List<ServiceAddscoretype> serviceAddscoretype=baseMapper.selectList(queryWrapper);
        List<VoselectAddscoretypeselectto> VoselectAddscoretypeselectto=new ArrayList<>();
        VoselectAddscoretypeselectto voselectAddscoretypeselectto=new VoselectAddscoretypeselectto();
        if(serviceAddscoretype.size()!=0){
            for(int i=0;i<serviceAddscoretype.size();i++){
                BeanUtils.copyProperties(serviceAddscoretype.get(i),voselectAddscoretypeselectto);
                VoselectAddscoretypeselectto.add(voselectAddscoretypeselectto);
            }
            return VoselectAddscoretypeselectto;
        }else{
            return VoselectAddscoretypeselectto;
        }
    }

    @Override//需要返回年份
    public List<VoselectAddscoretypeselectto1> ServiceAddscoretypeSelect1(VoselectAddscoretypeselect1 voselectAddscoretypeselect1) {
        QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voselectAddscoretypeselect1.getSchoolName());
        map.put("department",voselectAddscoretypeselect1.getDepartment());
        queryWrapper.allEq(map);
        queryWrapper.orderByDesc("year");
        queryWrapper.select("schoolName","department","selfTestScoreType","proportion","year");
        List<ServiceAddscoretype> serviceAddscoretype=baseMapper.selectList(queryWrapper);
        List<VoselectAddscoretypeselectto1> VoselectAddscoretypeselectto1=new ArrayList<>();
        VoselectAddscoretypeselectto1 voselectAddscoretypeselectto1=new VoselectAddscoretypeselectto1();
        if(serviceAddscoretype.size()!=0){
            for(int i=0;i<serviceAddscoretype.size();i++){
                BeanUtils.copyProperties(serviceAddscoretype.get(i),voselectAddscoretypeselectto1);
                VoselectAddscoretypeselectto1.add(voselectAddscoretypeselectto1);
            }
            return VoselectAddscoretypeselectto1;
        }else{
            return VoselectAddscoretypeselectto1;
        }
    }

    @Override//给学生用的
    public List<VoselectAddscoretypeselectto3> ServiceAddscoretypeSelectself(VoselectAddscoretypeselect voselectAddscoretypeselect) {
        QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voselectAddscoretypeselect.getSchoolName());
        map.put("department",voselectAddscoretypeselect.getDepartment());
        queryWrapper.allEq(map);
        List<VoselectAddscoretypeselectto3> VoselectAddscoretypeselectto3=new ArrayList<>();
        VoselectAddscoretypeselectto3 voselectAddscoretypeselectto3=new VoselectAddscoretypeselectto3();
        List<ServiceAddscoretype> ServiceAddscoretype=new ArrayList<>();
        ServiceAddscoretype=baseMapper.selectList(queryWrapper);
        if(ServiceAddscoretype.size()!=0){
            for(int i=0;i<ServiceAddscoretype.size();i++){
                BeanUtils.copyProperties(ServiceAddscoretype.get(i),voselectAddscoretypeselectto3);
                VoselectAddscoretypeselectto3.add(voselectAddscoretypeselectto3);
            }
            return VoselectAddscoretypeselectto3;
        }else{
            return VoselectAddscoretypeselectto3;
        }

    }
    @Transactional(rollbackFor = Exception.class)
    @Override//修改用的
    public void ServiceAddscoretypeupdate(VoAddscoretypeupdate voAddscoretypeupdate) {
        UpdateWrapper<ServiceAddscoretype> userUpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper<ServiceDeadline> userUpdateWrapper1 = new UpdateWrapper<>();
        UpdateWrapper<ServiceSelftotalscore> userUpdateWrapper2 = new UpdateWrapper<>();
        UpdateWrapper<ServiceTotalscore> userUpdateWrapper3 = new UpdateWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voAddscoretypeupdate.getSchoolName());
        map.put("department",voAddscoretypeupdate.getDepartment());
        map.put("year",voAddscoretypeupdate.getOldYear());
        //自测审核截止时间表
        userUpdateWrapper1.allEq(map);
        userUpdateWrapper1.set("year",voAddscoretypeupdate.getYear())
                .set("studentDeadline",voAddscoretypeupdate.getStudentDeadline())
                .set("managerDeadline",voAddscoretypeupdate.getManagerDeadline());
        try{
            ServiceDeadlineService.update(userUpdateWrapper1);//一般有这条记录都会更新成功//主要捕捉它，它没有记录下面肯定没记录
            //加分大类表，不用捕捉上面时间不一样这里就可以操作成功不会重复
            userUpdateWrapper.allEq(map);
            userUpdateWrapper.set("year",voAddscoretypeupdate.getYear())
                    .set("selfTestScoreType",voAddscoretypeupdate.getSelfTestScoreType())
                    .set("proportion",voAddscoretypeupdate.getProportion());
            this.update(userUpdateWrapper);
            //自测总表不用捕捉同上
            userUpdateWrapper2.allEq(map);
            userUpdateWrapper2.set("year",voAddscoretypeupdate.getYear());
            ServiceSelftotalscoreService.update(userUpdateWrapper2);
            //总表不用捕捉同上
            userUpdateWrapper3.allEq(map);
            userUpdateWrapper3.set("year",voAddscoretypeupdate.getYear());
            ServiceTotalscoreService.update(userUpdateWrapper3);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(100,"没有此记录修改自测审核截止时间失败出现异常请重新输入正确数据操作！");
        }

    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void ServiceAddscoretypedelete(VoAddscoretypedelete voAddscoretypedelete) {
        QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();
        QueryWrapper<ServiceDeadline> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<ServiceSelftotalscore> queryWrapper2 = new QueryWrapper<>();
        QueryWrapper<ServiceTotalscore> queryWrapper3 = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voAddscoretypedelete.getSchoolName());
        map.put("department",voAddscoretypedelete.getDepartment());
        map.put("year",voAddscoretypedelete.getYear());
        queryWrapper.allEq(map);
        queryWrapper1.allEq(map);
        queryWrapper2.allEq(map);
        queryWrapper3.allEq(map);
        try{
            ServiceDeadlineMapper.delete(queryWrapper1);//主要捕捉它，它没有记录下面肯定没记录
            baseMapper.delete(queryWrapper);
            ServiceSelftotalscoreMapper.delete(queryWrapper2);
            ServiceTotalscoreMapper.delete(queryWrapper3);
        }catch(ServiceException E){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(100,"删除自测审核截止时间异常请输入正确数据重新操作!");
        }

    }

}
