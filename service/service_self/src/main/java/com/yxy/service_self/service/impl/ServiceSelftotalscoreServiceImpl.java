package com.yxy.service_self.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_self.bean.ServiceSelftotalscore;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscore;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscore1;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscoreto;
import com.yxy.service_self.bean.vo.voServiceSelftotalscore.VoServiceSelftotalscoreto1;
import com.yxy.service_self.mapper.ServiceSelftotalscoreMapper;
import com.yxy.service_self.service.ServiceSelftotalscoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自测总成绩表 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Service
public class ServiceSelftotalscoreServiceImpl extends ServiceImpl<ServiceSelftotalscoreMapper, ServiceSelftotalscore> implements ServiceSelftotalscoreService {
    @Override
    public List<VoServiceSelftotalscoreto> selectSelftotalscore(VoServiceSelftotalscore voServiceSelftotalscore) {
        List<ServiceSelftotalscore> serviceSelftotalscore=new ArrayList<>();
        List<VoServiceSelftotalscoreto> voServiceSelftotalscoreto=new ArrayList<>();
        QueryWrapper<ServiceSelftotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId",  voServiceSelftotalscore.getUserId());
        map.put("schoolName",  voServiceSelftotalscore.getSchoolName());
        queryWrapper.allEq(map);
        queryWrapper.orderByDesc("selfTestTotalScore");
        serviceSelftotalscore=baseMapper.selectList(queryWrapper);
        if(serviceSelftotalscore.size()!=0){
            VoServiceSelftotalscoreto voServiceSelftotalscoreto2=new VoServiceSelftotalscoreto();
            for(int i=0;i<serviceSelftotalscore.size();i++){
                BeanUtils.copyProperties(serviceSelftotalscore.get(i),voServiceSelftotalscoreto2);
                voServiceSelftotalscoreto.add(voServiceSelftotalscoreto2);
            }
            return voServiceSelftotalscoreto;
        }else{
            return voServiceSelftotalscoreto;
        }
    }

    @Override
    public List<VoServiceSelftotalscoreto1> selectSelftotalscore1(VoServiceSelftotalscore voServiceSelftotalscore) {
        List<ServiceSelftotalscore> serviceSelftotalscore=new ArrayList<>();
        List<VoServiceSelftotalscoreto1> voServiceSelftotalscoreto1=new ArrayList<>();
        QueryWrapper<ServiceSelftotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId",  voServiceSelftotalscore.getUserId());
        map.put("schoolName",  voServiceSelftotalscore.getSchoolName());
        map.put("year",  voServiceSelftotalscore.getYear());
        queryWrapper.allEq(map);
        queryWrapper.orderByDesc("selfTestTotalScore");
        serviceSelftotalscore=baseMapper.selectList(queryWrapper);
        if(serviceSelftotalscore.size()!=0){
            VoServiceSelftotalscoreto1 voServiceSelftotalscoreto2=new VoServiceSelftotalscoreto1();
            for(int i=0;i<serviceSelftotalscore.size();i++){
                BeanUtils.copyProperties(serviceSelftotalscore.get(i),voServiceSelftotalscoreto2);
                voServiceSelftotalscoreto1.add(voServiceSelftotalscoreto2);
            }
            return voServiceSelftotalscoreto1;
        }else{
            return voServiceSelftotalscoreto1;
        }
    }

    @Override
    public List<VoServiceSelftotalscoreto1> selectSelftotalscore2(VoServiceSelftotalscore1 voServiceSelftotalscore1) {
        List<ServiceSelftotalscore> serviceSelftotalscore=new ArrayList<>();
        List<VoServiceSelftotalscoreto1> voServiceSelftotalscoreto1=new ArrayList<>();
        QueryWrapper<ServiceSelftotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName",  voServiceSelftotalscore1.getSchoolName());
        map.put("department",  voServiceSelftotalscore1.getDepartment());
        map.put("majorName",  voServiceSelftotalscore1.getMajorName());
        map.put("className",  voServiceSelftotalscore1.getClassName());
        map.put("year",  voServiceSelftotalscore1.getYear());
        queryWrapper.allEq(map);
        queryWrapper.orderByDesc("selfTestTotalScore");
        serviceSelftotalscore=baseMapper.selectList(queryWrapper);
        if(serviceSelftotalscore.size()!=0){
            for(int i=0;i<serviceSelftotalscore.size();i++){
                VoServiceSelftotalscoreto1 voServiceSelftotalscoreto2=new VoServiceSelftotalscoreto1();
                BeanUtils.copyProperties(serviceSelftotalscore.get(i),voServiceSelftotalscoreto2);
                voServiceSelftotalscoreto1.add(voServiceSelftotalscoreto2);
            }
            return voServiceSelftotalscoreto1;
        }else{
            return voServiceSelftotalscoreto1;
        }
    }

    @Override
    public List<VoServiceSelftotalscoreto1> selectSelftotalscore3(VoServiceSelftotalscore1 voServiceSelftotalscore2) {
        List<ServiceSelftotalscore> serviceSelftotalscore=new ArrayList<>();
        List<VoServiceSelftotalscoreto1> voServiceSelftotalscoreto1=new ArrayList<>();
        QueryWrapper<ServiceSelftotalscore> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName",  voServiceSelftotalscore2.getSchoolName());
        map.put("department",  voServiceSelftotalscore2.getDepartment());
        map.put("majorName",  voServiceSelftotalscore2.getMajorName());
        map.put("year",  voServiceSelftotalscore2.getYear());
        queryWrapper.allEq(map);
        queryWrapper.orderByDesc("selfTestTotalScore");
        serviceSelftotalscore=baseMapper.selectList(queryWrapper);
        if(serviceSelftotalscore.size()!=0){
            for(int i=0;i<serviceSelftotalscore.size();i++){
                VoServiceSelftotalscoreto1 voServiceSelftotalscoreto2=new VoServiceSelftotalscoreto1();
                BeanUtils.copyProperties(serviceSelftotalscore.get(i),voServiceSelftotalscoreto2);
                voServiceSelftotalscoreto1.add(voServiceSelftotalscoreto2);
            }
            return voServiceSelftotalscoreto1;
        }else{
            return voServiceSelftotalscoreto1;
        }
    }

}
