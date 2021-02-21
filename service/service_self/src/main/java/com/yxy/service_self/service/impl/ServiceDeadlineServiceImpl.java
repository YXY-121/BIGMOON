package com.yxy.service_self.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_self.bean.ServiceDeadline;
import com.yxy.service_self.bean.vo.voServiceDeadline.VoServiceDeadline;
import com.yxy.service_self.bean.vo.voServiceDeadline.VoServiceDeadlineto;
import com.yxy.service_self.mapper.ServiceAddscoretypeMapper;
import com.yxy.service_self.mapper.ServiceDeadlineMapper;
import com.yxy.service_self.service.ServiceDeadlineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自测截止审核时间表 服务实现类
 * </p>

 * @author yxy
 * @since 2020-12-13
 */
@Service
public class ServiceDeadlineServiceImpl extends ServiceImpl<ServiceDeadlineMapper, ServiceDeadline> implements ServiceDeadlineService {
    @Autowired
    com.yxy.service_self.mapper.ServiceAddscoretypeMapper ServiceAddscoretypeMapper;
    @Override
    public List<VoServiceDeadlineto> selectDeadline(VoServiceDeadline voServiceDeadline) {
        QueryWrapper<ServiceDeadline> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceDeadline.getSchoolName());
        map.put("department",voServiceDeadline.getDepartment());
        if(voServiceDeadline.equals("all")==false){
            map.put("year",voServiceDeadline.getYear());
            queryWrapper.allEq(map);
            queryWrapper.select("schoolName","department","year","studentDeadline","managerDeadline");
            List<ServiceDeadline> serviceDeadline=baseMapper.selectList(queryWrapper);
            List<VoServiceDeadlineto> deadlineto=new ArrayList<>();
            if(serviceDeadline.size()!=0){
                VoServiceDeadlineto voServiceDeadlineto=new VoServiceDeadlineto();
                for(int i=0;i<serviceDeadline.size();i++){
                    BeanUtils.copyProperties(serviceDeadline.get(i),voServiceDeadlineto);
                    deadlineto.add(voServiceDeadlineto);
                }
                return deadlineto;
            }else{
                return deadlineto;
            }
        }else{
            queryWrapper.allEq(map);
            queryWrapper.select("schoolName","department","year","studentDeadline","managerDeadline");
            List<ServiceDeadline> serviceDeadline=baseMapper.selectList(queryWrapper);
            List<VoServiceDeadlineto> deadlineto=new ArrayList<>();
            if(serviceDeadline.size()!=0){
                VoServiceDeadlineto voServiceDeadlineto=new VoServiceDeadlineto();
                for(int i=0;i<serviceDeadline.size();i++){
                    BeanUtils.copyProperties(serviceDeadline.get(i),voServiceDeadlineto);
                    deadlineto.add(voServiceDeadlineto);
                }
                return deadlineto;
            }else{
                return deadlineto;
            }
        }
    }
/*
    @Override//年份修改去初始化哪里
    public int updateDeadline(VoServiceDeadlineto voServiceDeadlineto) {


        ServiceDeadline serviceDeadline=new ServiceDeadline();
        //修改条件
        UpdateWrapper<ServiceDeadline> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("selftestdeadline",voServiceDeadlineto.getSelftestdeadline())
                .set("checkdeadline",voServiceDeadlineto.getCheckdeadline());
        QueryWrapper<ServiceDeadline> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceTotalscore.getSchoolName());
        map.put("majorName", voServiceTotalscore.getMajorName());

        int i=baseMapper.update();
        return 0;
    }*/
}
