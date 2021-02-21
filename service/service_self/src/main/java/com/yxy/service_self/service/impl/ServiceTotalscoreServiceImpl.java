package com.yxy.service_self.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.serviceBase.resultCode.ResultUtils;
import com.yxy.service_self.bean.ServiceTotalscore;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoServiceTotalscore;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoServiceTotalscoreto;
import com.yxy.service_self.bean.vo.voTotalscoreto.VoUpdatetatolscore;
import com.yxy.service_self.mapper.ServiceTotalscoreMapper;
import com.yxy.service_self.service.ServiceTotalscoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.serviceBase.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 总成绩表 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Service
public class ServiceTotalscoreServiceImpl extends ServiceImpl<ServiceTotalscoreMapper, ServiceTotalscore> implements ServiceTotalscoreService {

    @Override
    public List<VoServiceTotalscoreto> selectTotalAll(VoServiceTotalscore voServiceTotalscore) {
        QueryWrapper<ServiceTotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceTotalscore.getSchoolName());
        map.put("className", voServiceTotalscore.getClassName());
        if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==true){
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }

        }else if(voServiceTotalscore.getYear().equals("all")==false&&voServiceTotalscore.getStatus().equals("all")==true){
            map.put("year", voServiceTotalscore.getYear());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==false){
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else{
            map.put("year", voServiceTotalscore.getYear());
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }
    }

    @Override
    public List<VoServiceTotalscoreto> selectTotalSelf(VoServiceTotalscore voServiceTotalscore) {
        QueryWrapper<ServiceTotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceTotalscore.getSchoolName());
        map.put("userId", voServiceTotalscore.getUserId());
        if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==true){
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==false&&voServiceTotalscore.getStatus().equals("all")==true){
            map.put("year", voServiceTotalscore.getYear());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==false){
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else{
            map.put("year", voServiceTotalscore.getYear());
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }
    }

    @Override//按同个学校每个班级
    public List<VoServiceTotalscoreto> selectTotalSuper1(VoServiceTotalscore voServiceTotalscore) {
        QueryWrapper<ServiceTotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceTotalscore.getSchoolName());
        map.put("className", voServiceTotalscore.getClassName());
        if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==true){
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==false&&voServiceTotalscore.getStatus().equals("all")==true){
            map.put("year", voServiceTotalscore.getYear());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==false){
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else{
            map.put("year", voServiceTotalscore.getYear());
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }
    }

    @Override
    public List<VoServiceTotalscoreto> selectTotalSuper2(VoServiceTotalscore voServiceTotalscore) {
        QueryWrapper<ServiceTotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceTotalscore.getSchoolName());
        map.put("department", voServiceTotalscore.getDepartment());
        map.put("majorName", voServiceTotalscore.getMajorName());
        if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==true){
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==false&&voServiceTotalscore.getStatus().equals("all")==true){
            map.put("year", voServiceTotalscore.getYear());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else if(voServiceTotalscore.getYear().equals("all")==true&&voServiceTotalscore.getStatus().equals("all")==false){
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }else{
            map.put("year", voServiceTotalscore.getYear());
            map.put("status", voServiceTotalscore.getStatus());
            queryWrapper.allEq(map);
            queryWrapper.select("userId","className","department","majorName","schoolName", "year","selfStudyScore","selfTestTotalScore","totalScore","status");
            queryWrapper.orderByDesc("totalScore");
            List<ServiceTotalscore> serviceTotalscore = baseMapper.selectList(queryWrapper);
            List<VoServiceTotalscoreto> voServiceTotalscoreto =new ArrayList<>();
            if(serviceTotalscore.size()!=0){
                VoServiceTotalscoreto voServiceTotalscoreto1=new VoServiceTotalscoreto();
                for(int i=0;i<serviceTotalscore.size();i++){
                    BeanUtils.copyProperties(serviceTotalscore.get(i),voServiceTotalscoreto1);
                    voServiceTotalscoreto.add(voServiceTotalscoreto1);
                }
                return voServiceTotalscoreto;
            }else{
                return voServiceTotalscoreto;
            }
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTotal(VoUpdatetatolscore voUpdatetatolscore) {
        QueryWrapper<ServiceTotalscore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", voUpdatetatolscore.getUserId());
        queryWrapper.eq("schoolName", voUpdatetatolscore.getSchoolName());
        queryWrapper.eq("year", voUpdatetatolscore.getYear());
       /* map.put("userId", voUpdatetatolscore.getUserId());
        map.put("schoolName", voUpdatetatolscore.getSchoolName());
        map.put("majorName", voUpdatetatolscore.getMajorName());
        map.put("className", voUpdatetatolscore.getClassName());
        map.put("year", voUpdatetatolscore.getYear());
        queryWrapper.allEq(map);*/
        ServiceTotalscore serviceTotalscore=new ServiceTotalscore();
        BeanUtils.copyProperties(voUpdatetatolscore,serviceTotalscore);
        try{
            baseMapper.update(serviceTotalscore,queryWrapper);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(100,"修改更新失败出现异常请重新操作！");
        }
    }

    @Override
    public void totalScore(String userId, String schoolName, String  year) {
        QueryWrapper<ServiceTotalscore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("schoolName", schoolName);
        queryWrapper.eq("year", year);
        ServiceTotalscore serviceTotalscore=baseMapper.selectOne(queryWrapper);
        serviceTotalscore.setTotalScore(serviceTotalscore.getSelfTestTotalScore()+serviceTotalscore.getSelfStudyScore());
        baseMapper.update(serviceTotalscore,queryWrapper);
       /*
       //初始化就用这个同学所以肯定可以更新
        int i= baseMapper.update(serviceTotalscore,queryWrapper);
       if(i==0){
            throw new ServiceException(100,"系统异常综测总成绩更新失败!");//在controller那里捕捉
        }*/
    }
}
