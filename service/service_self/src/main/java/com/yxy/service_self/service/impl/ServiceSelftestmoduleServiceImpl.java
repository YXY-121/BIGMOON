package com.yxy.service_self.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.yxy.oss.provider.FilemoduleproviderService;
import com.yxy.serviceBase.exception.ServiceException;
import com.yxy.service_self.bean.ServiceAddscoretype;
import com.yxy.service_self.bean.ServiceSelftestmodule;
import com.yxy.service_self.bean.ServiceSelftotalscore;
import com.yxy.service_self.bean.ServiceTotalscore;
import com.yxy.service_self.bean.vo.voServiceSelftestmodule.*;
import com.yxy.service_self.mapper.ServiceAddscoretypeMapper;
import com.yxy.service_self.mapper.ServiceSelftestmoduleMapper;
import com.yxy.service_self.mapper.ServiceSelftotalscoreMapper;
import com.yxy.service_self.mapper.ServiceTotalscoreMapper;
import com.yxy.service_self.service.ServiceSelftestmoduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.service_self.service.ServiceTotalscoreService;


import com.yxy.service_userCenter.bean.vo.VoServiceUser3;
import com.yxy.service_userCenter.bean.vo.VoServiceUser4;
import com.yxy.service_userCenter.bean.vo.VoServiceUserto;
import com.yxy.service_userCenter.provider.ServiceproviderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自测模块表 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Service
public class ServiceSelftestmoduleServiceImpl extends ServiceImpl<ServiceSelftestmoduleMapper, ServiceSelftestmodule> implements ServiceSelftestmoduleService {
    //导入加分大类表、自测模块总表、总表mapper以及总表的接口为了调用
    @Autowired
    ServiceAddscoretypeMapper serviceAddscoretypeMapper;
    @Autowired
    ServiceSelftotalscoreMapper serviceSelftotalscoreMapper;
    @Autowired
    ServiceTotalscoreService serviceTotalscoreService;
    @Autowired
    ServiceTotalscoreMapper serviceTotalscoreMapper;
    //(parameters = {"protocol","hessian"})
    @Reference(parameters = {"protocol","osshessian"})
    FilemoduleproviderService filemoduleproviderService;
    @Reference(parameters = {"protocol","userCenterhessian"})
    ServiceproviderService ServiceproviderService;
    @Transactional(rollbackFor = Exception.class)
    @Override//自测模块插入,不用进行是否有这个分数类型的判断因为分数类型是查询出来选的,记得返回ID值
    public List<voServiceSelftestmoduleto> saveadd(List<voServiceSelftestmodule> voServiceSelftestmodule) {
        List<ServiceSelftestmodule> ServiceSelftestmodule=new ArrayList<>();
        ServiceSelftestmodule ServiceSelftestmodule1=new ServiceSelftestmodule();
       // BeanUtil.copyProperties(voServiceSelftestmodule,vofile);//没有什么用了本来用来oss的图片加上学校学校分数类型文件夹结果，oss的访问路径不能有中文
         for(int k=0;k<voServiceSelftestmodule.size();k++){
                      BeanUtils.copyProperties(voServiceSelftestmodule.get(k),ServiceSelftestmodule1);
                      ServiceSelftestmodule.add(ServiceSelftestmodule1);
               }
           try{
               for(int g=0;g<ServiceSelftestmodule.size();g++){
                   baseMapper.insert(ServiceSelftestmodule.get(g));//主要是捕捉它，它插不入下面肯定不行
               }
               //根据学校学号分数类型年份去查询这个同学这一年的这个分数类型的自测的总和
               QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();//加分大类表的
               QueryWrapper<ServiceSelftotalscore> queryWrapper2 = new QueryWrapper<>();//自测总表的
               QueryWrapper<ServiceTotalscore > queryWrapper1 = new QueryWrapper<>();//总表的
               QueryWrapper<ServiceSelftestmodule> queryWrapper3 = new QueryWrapper<>();//自测模块表的
               Map<String, Object> map = new HashMap<>();
               //同一个人条件一样取第一个
               map.put("schoolName",voServiceSelftestmodule.get(0).getSchoolName());
               map.put("userId",voServiceSelftestmodule.get(0).getUserId());
               map.put("year",voServiceSelftestmodule.get(0).getYear());
               float sumall=0;//自测总分
               //先从加分大类表把这个同学对应的这个学校今年所有加分类型查询出来
               queryWrapper.allEq(map);
               List<ServiceAddscoretype> ServiceAddscoretype=new ArrayList<>();
               ServiceAddscoretype=serviceAddscoretypeMapper.selectList(queryWrapper);
               //根据加分大类从自测模块表计算出每个的总分并且所有类的总分叠加起来
               for(int j=0;j<ServiceAddscoretype.size();j++) {
                   map.put("selfTestScoreType", ServiceAddscoretype.get(j).getSelfTestScoreType());
                   queryWrapper3.allEq(map);
                   //自己在mapper文件里写个函数
                   sumall = sumall + baseMapper.sumScoreall(queryWrapper3);
               }
               //更新这位同学自测总表的总分
               queryWrapper2.eq("schoolName",voServiceSelftestmodule.get(0).getSchoolName()).eq("userId",voServiceSelftestmodule.get(0).getUserId()).eq("year",voServiceSelftestmodule.get(0).getYear());
               ServiceSelftotalscore serviceSelftotalscore= serviceSelftotalscoreMapper.selectOne(queryWrapper2);
               serviceSelftotalscore.setSelfTestTotalScore(sumall);
               serviceSelftotalscoreMapper.update(serviceSelftotalscore,queryWrapper2);
               //更新这个的总表成绩(先更新他的自测总成绩，再更新总成绩)
               queryWrapper1.eq("schoolName",voServiceSelftestmodule.get(0).getSchoolName()).eq("userId",voServiceSelftestmodule.get(0).getUserId()).eq("year",voServiceSelftestmodule.get(0).getYear());
               ServiceTotalscore ServiceTotalscore=serviceTotalscoreMapper.selectOne(queryWrapper1);
               ServiceTotalscore.setSelfTestTotalScore(sumall);
               serviceTotalscoreMapper.update(ServiceTotalscore,queryWrapper1);
               serviceTotalscoreService.totalScore(voServiceSelftestmodule.get(0).getUserId(),voServiceSelftestmodule.get(0).getSchoolName(),voServiceSelftestmodule.get(0).getYear());
           }catch (Exception e){
               //事务回滚前删除刚刚上传的文件
               List<String> urls=new ArrayList<>();
               for(int y=0;y< ServiceSelftestmodule.size();y++){
                   urls.add(ServiceSelftestmodule.get(y).getUrl());
               }
               filemoduleproviderService.deleteFile(urls);
               TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 事务回滚
               throw new  ServiceException(100,"提交失败请重新操作！");
           }
       //返回复制
        List<voServiceSelftestmoduleto> voServiceSelftestmoduleto=new ArrayList<>();
       voServiceSelftestmoduleto voServiceSelftestmoduleto1=new voServiceSelftestmoduleto();
       for(int f=0;f<ServiceSelftestmodule.size();f++){
           BeanUtils.copyProperties(ServiceSelftestmodule.get(f),voServiceSelftestmoduleto1);
           voServiceSelftestmoduleto.add(voServiceSelftestmoduleto1);
       }
        return voServiceSelftestmoduleto;//id自动回填
    }
    //上传多个文件
    @Override
    public List<String> upload(MultipartFile[] files) {
        List<String> urls=new ArrayList<>();
        try{
            urls=filemoduleproviderService.upload(files);//多个图片上传,上传文件要是失败就不用下面插了
        }catch (ServiceException serviceException){
            throw new  ServiceException(100,"文件上传失败请重新操作！");
        }
       return urls;
    }
    //删除多个文件这个函数用于更新用
    @Override
    public void deletefiles(List<String> urls) {
        try{
            filemoduleproviderService.deleteFile(urls); ;//多个图片上传,上传文件要是失败就不用下面插了
        }catch (ServiceException serviceException){
            throw new  ServiceException(100,"文件上传失败请重新操作！");
        }

    }
    @Transactional(rollbackFor = Exception.class)
    @Override//删除函数
    public void deleteself(List<voServiceSelftestmoduledelete> voServiceSelftestmoduledelete) {
       try{
           //赋值文件路径
           List<String> urls=new ArrayList<>();
           for(int i=0;i<voServiceSelftestmoduledelete.size();i++){
               urls.add(voServiceSelftestmoduledelete.get(i).getUrl());
           }
           //删除自测表中记录
           filemoduleproviderService.deleteFile(urls);
           List<String > selfTestId=new ArrayList<>();
           for(int j=0;j<voServiceSelftestmoduledelete.size();j++){
               selfTestId.add(voServiceSelftestmoduledelete.get(j).getSelfTestId());//拿ID不是拿对象不需要复制
           }
           baseMapper.deleteBatchIds(selfTestId);
           //各个模块的QueryWrapper
           //根据学校学号分数类型年份去查询这个同学这一年的这个分数类型的自测的总和
           QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();//加分大类表的
           QueryWrapper<ServiceSelftotalscore> queryWrapper2 = new QueryWrapper<>();//自测总表的
           QueryWrapper<ServiceTotalscore > queryWrapper1 = new QueryWrapper<>();//总表的
           QueryWrapper<ServiceSelftestmodule> queryWrapper3 = new QueryWrapper<>();//自测模块表的
           Map<String, Object> map = new HashMap<>();
           //同一个人条件一样取第一个
           map.put("schoolName",voServiceSelftestmoduledelete.get(0).getSchoolName());
           map.put("userId",voServiceSelftestmoduledelete.get(0).getUserId());
           map.put("year",voServiceSelftestmoduledelete.get(0).getYear());
           //更新自测成绩表
           float sumall=0;//自测总分
           queryWrapper.allEq(map);
           List<ServiceAddscoretype> ServiceAddscoretype=new ArrayList<>();
           ServiceAddscoretype=serviceAddscoretypeMapper.selectList(queryWrapper);
           //根据加分大类从自测模块表计算出每个的总分并且所有类的总分叠加起来
           for(int j=0;j<ServiceAddscoretype.size();j++) {
               map.put("selfTestScoreType", ServiceAddscoretype.get(j).getSelfTestScoreType());
               queryWrapper3.allEq(map);
               //自己在mapper文件里写个函数
               sumall = sumall + baseMapper.sumScoreall(queryWrapper3);
           }
           //更新这位同学自测总表的总分
           queryWrapper2.eq("schoolName",voServiceSelftestmoduledelete.get(0).getSchoolName()).eq("userId",voServiceSelftestmoduledelete.get(0).getUserId()).eq("year",voServiceSelftestmoduledelete.get(0).getYear());
           ServiceSelftotalscore serviceSelftotalscore= serviceSelftotalscoreMapper.selectOne(queryWrapper2);
           serviceSelftotalscore.setSelfTestTotalScore(sumall);
           serviceSelftotalscoreMapper.update(serviceSelftotalscore,queryWrapper2);
           //更新总表成绩
           queryWrapper1.eq("schoolName",voServiceSelftestmoduledelete.get(0).getSchoolName()).eq("userId",voServiceSelftestmoduledelete.get(0).getUserId()).eq("year",voServiceSelftestmoduledelete.get(0).getYear());
           ServiceTotalscore ServiceTotalscore=serviceTotalscoreMapper.selectOne(queryWrapper1);
           ServiceTotalscore.setSelfTestTotalScore(sumall);
           serviceTotalscoreMapper.update(ServiceTotalscore,queryWrapper1);
           serviceTotalscoreService.totalScore(voServiceSelftestmoduledelete.get(0).getUserId(),voServiceSelftestmoduledelete.get(0).getSchoolName(),voServiceSelftestmoduledelete.get(0).getYear());
       }catch (ServiceException ServiceException){
           //这里不需要回滚操作因为阿里云那边删不了抛出错误这边肯定没执行
           //这里捕捉阿里云删除文件的异常，那边出了任何异常抛出ServiceException异常
           throw new ServiceException(100,"文件删除失败请重新操作！");
       }catch (Exception e){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 事务回滚
           throw new ServiceException(100,"操作失败请重新操作！");
       }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateself(List<voServiceSelftestmoduleupdate> voServiceSelftestmoduleupdate) {
        List<ServiceSelftestmodule> ServiceSelftestmodule=new ArrayList<>();
        ServiceSelftestmodule ServiceSelftestmodule1=new ServiceSelftestmodule();
        // BeanUtil.copyProperties(voServiceSelftestmodule,vofile);//没有什么用了本来用来oss的图片加上学校学校分数类型文件夹结果，oss的访问路径不能有中文
        for(int k=0;k<voServiceSelftestmoduleupdate.size();k++){
            BeanUtils.copyProperties(voServiceSelftestmoduleupdate.get(k),ServiceSelftestmodule1);
            ServiceSelftestmodule.add(ServiceSelftestmodule1);

        }
        //复制selftestmoduleId
        /*List<String > selftestmoduleId=new ArrayList<>();
        for(int j=0;j<voServiceSelftestmoduleupdate.size();j++){
            selftestmoduleId.add(voServiceSelftestmoduleupdate.get(j).getSelftestmoduleId());//拿ID不是拿对象不需要复制
        }*/
        try{
            //根据selftestmoduleId去更新自测加分表但是这里的参数是ServiceSelftestmodule对象
            for(int  i=0;i<ServiceSelftestmodule.size();i++){
                baseMapper.updateById(ServiceSelftestmodule.get(i))  ;
            }
            //各个模块的QueryWrapper
            //根据学校学号分数类型年份去查询这个同学这一年的这个分数类型的自测的总和
            QueryWrapper<ServiceAddscoretype> queryWrapper = new QueryWrapper<>();//加分大类表的
            QueryWrapper<ServiceSelftotalscore> queryWrapper2 = new QueryWrapper<>();//自测总表的
            QueryWrapper<ServiceTotalscore > queryWrapper1 = new QueryWrapper<>();//总表的
            QueryWrapper<ServiceSelftestmodule> queryWrapper3 = new QueryWrapper<>();//自测模块表的
            Map<String, Object> map = new HashMap<>();
            //同一个人条件一样取第一个
            map.put("schoolName",voServiceSelftestmoduleupdate.get(0).getSchoolName());
            map.put("userId",voServiceSelftestmoduleupdate.get(0).getUserId());
            map.put("year",voServiceSelftestmoduleupdate.get(0).getYear());
            //更新自测成绩表
            float sumall=0;//自测总分
            queryWrapper.allEq(map);
            List<ServiceAddscoretype> ServiceAddscoretype=new ArrayList<>();
            ServiceAddscoretype=serviceAddscoretypeMapper.selectList(queryWrapper);
            //根据加分大类从自测模块表计算出每个的总分并且所有类的总分叠加起来
            for(int j=0;j<ServiceAddscoretype.size();j++) {
                map.put("selfTestScoreType", ServiceAddscoretype.get(j).getSelfTestScoreType());
                queryWrapper3.allEq(map);
                //自己在mapper文件里写个函数
                sumall = sumall + baseMapper.sumScoreall(queryWrapper3);
            }
            //更新这位同学自测总表的总分
            queryWrapper2.eq("schoolName",voServiceSelftestmoduleupdate.get(0).getSchoolName()).eq("userId",voServiceSelftestmoduleupdate.get(0).getUserId()).eq("year",voServiceSelftestmoduleupdate.get(0).getYear());
            ServiceSelftotalscore serviceSelftotalscore= serviceSelftotalscoreMapper.selectOne(queryWrapper2);
            serviceSelftotalscore.setSelfTestTotalScore(sumall);
            serviceSelftotalscoreMapper.update(serviceSelftotalscore,queryWrapper2);
            //更新总表成绩
            queryWrapper1.eq("schoolName",voServiceSelftestmoduleupdate.get(0).getSchoolName()).eq("userId",voServiceSelftestmoduleupdate.get(0).getUserId()).eq("year",voServiceSelftestmoduleupdate.get(0).getYear());
            ServiceTotalscore ServiceTotalscore=serviceTotalscoreMapper.selectOne(queryWrapper1);
            ServiceTotalscore.setSelfTestTotalScore(sumall);
            serviceTotalscoreMapper.update(ServiceTotalscore,queryWrapper1);
            serviceTotalscoreService.totalScore(voServiceSelftestmoduleupdate.get(0).getUserId(),voServiceSelftestmoduleupdate.get(0).getSchoolName(),voServiceSelftestmoduleupdate.get(0).getYear());
    }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 事务回滚
            throw new ServiceException(100,"更新操作失败请重新操作！");
        }
    }
   //需要返回年份的
    @Override
    public List<voServiceSelftestmoduleto1> selectself(voServiceSelftestmoduleselect voServiceSelftestmoduleselect) {
        QueryWrapper<ServiceSelftestmodule> queryWrapper = new QueryWrapper<>();//自测模块表的
        List<ServiceSelftestmodule> ServiceSelftestmodule=new ArrayList<>();
        List<voServiceSelftestmoduleto1> voServiceSelftestmoduleto1=new ArrayList<>();
        voServiceSelftestmoduleto1 voServiceSelftestmoduleto2=new voServiceSelftestmoduleto1();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName",voServiceSelftestmoduleselect.getSchoolName());
        map.put("selfTestScoreType",voServiceSelftestmoduleselect.getSelfTestScoreType());
        map.put("userId",voServiceSelftestmoduleselect.getUserId());
        queryWrapper.allEq(map);
        ServiceSelftestmodule=baseMapper.selectList(queryWrapper);
        for(int i=0;i<ServiceSelftestmodule.size();i++){
            BeanUtils.copyProperties(ServiceSelftestmodule.get(i),voServiceSelftestmoduleto2);
            voServiceSelftestmoduleto1.add(voServiceSelftestmoduleto2);
        }
        return voServiceSelftestmoduleto1 ;
    }
   //不需要返回年份的
    @Override//普通学生或者班长按分数类型年份查看
    public List<voServiceSelftestmoduleto2> selectself1(voServiceSelftestmoduleselect voServiceSelftestmoduleselect) {
        QueryWrapper<ServiceSelftestmodule> queryWrapper = new QueryWrapper<>();//自测模块表的
        List<ServiceSelftestmodule> ServiceSelftestmodule = new ArrayList<>();
        List<voServiceSelftestmoduleto2> voServiceSelftestmoduleto2 = new ArrayList<>();
        voServiceSelftestmoduleto2 voServiceSelftestmoduleto3 = new voServiceSelftestmoduleto2();
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", voServiceSelftestmoduleselect.getSchoolName());
        map.put("selfTestScoreType", voServiceSelftestmoduleselect.getSelfTestScoreType());
        map.put("userId", voServiceSelftestmoduleselect.getUserId());
        queryWrapper.allEq(map);
        ServiceSelftestmodule = baseMapper.selectList(queryWrapper);
        for (int i = 0; i < ServiceSelftestmodule.size(); i++) {
            BeanUtils.copyProperties(ServiceSelftestmodule.get(i), voServiceSelftestmoduleto3);
            voServiceSelftestmoduleto2.add(voServiceSelftestmoduleto3);
        }
        return voServiceSelftestmoduleto2;
    }

    @Override//班长按分数类型年份查看自己班
    public List<voServiceSelftestmoduleto2> selectself2(voServiceSelftestmoduleselect1 voServiceSelftestmoduleselect1) {
        QueryWrapper<ServiceSelftestmodule> queryWrapper = new QueryWrapper<>();//自测模块表的
        List<voServiceSelftestmoduleto2> voServiceSelftestmoduleto2 = new ArrayList<>();
        voServiceSelftestmoduleto2 voServiceSelftestmoduleto3=new voServiceSelftestmoduleto2();
        VoServiceUser4 VoServiceUser4=new VoServiceUser4();
        VoServiceUser4.setSchoolName(voServiceSelftestmoduleselect1.getSchoolName());
        VoServiceUser4.setDepartment(voServiceSelftestmoduleselect1.getDepartment());
        VoServiceUser4.setClassName(voServiceSelftestmoduleselect1.getClassName());
        List<VoServiceUserto> VoServiceUserto=ServiceproviderService.selectUser3(VoServiceUser4);//得到每个同学学号
        for(int i=0;i<VoServiceUserto.size();i++){
            //一定要放在里面因为需要每次的map、ServiceSelftestmodule不一样，学号不一样
            List<ServiceSelftestmodule> ServiceSelftestmodule = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("schoolName", voServiceSelftestmoduleselect1.getSchoolName());
            map.put("selfTestScoreType", voServiceSelftestmoduleselect1.getSelfTestScoreType());
            map.put("year", voServiceSelftestmoduleselect1.getYear());
            map.put("userId",VoServiceUserto.get(i));
            queryWrapper.allEq(map);
            ServiceSelftestmodule=baseMapper.selectList(queryWrapper);
            for(int j=0;j<ServiceSelftestmodule.size();j++){
                BeanUtils.copyProperties(ServiceSelftestmodule.get(i),voServiceSelftestmoduleto3);
                voServiceSelftestmoduleto2.add(voServiceSelftestmoduleto3);
            }
        }
        return voServiceSelftestmoduleto2;
    }

    @Override
    public List<voServiceSelftestmoduleto2> selectself3(voServiceSelftestmoduleselect2 voServiceSelftestmoduleselect2) {
        QueryWrapper<ServiceSelftestmodule> queryWrapper = new QueryWrapper<>();//自测模块表的
        List<voServiceSelftestmoduleto2> voServiceSelftestmoduleto2 = new ArrayList<>();
        voServiceSelftestmoduleto2 voServiceSelftestmoduleto3=new voServiceSelftestmoduleto2();
        VoServiceUser3 VoServiceUser3=new VoServiceUser3();
        VoServiceUser3.setSchoolName(voServiceSelftestmoduleselect2.getSchoolName());
        VoServiceUser3.setDepartment(voServiceSelftestmoduleselect2.getDepartment());
        VoServiceUser3.setMajorName(voServiceSelftestmoduleselect2.getMajorName());
        List<VoServiceUserto> VoServiceUserto=ServiceproviderService.selectUser4(VoServiceUser3);//得到每个同学学号
        for(int i=0;i<VoServiceUserto.size();i++){
            //一定要放在里面因为需要每次的map、ServiceSelftestmodule不一样，学号不一样
            List<ServiceSelftestmodule> ServiceSelftestmodule = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("schoolName", voServiceSelftestmoduleselect2.getSchoolName());
            map.put("selfTestScoreType", voServiceSelftestmoduleselect2.getSelfTestScoreType());
            map.put("year", voServiceSelftestmoduleselect2.getYear());
            map.put("userId",VoServiceUserto.get(i));
            queryWrapper.allEq(map);
            ServiceSelftestmodule=baseMapper.selectList(queryWrapper);
            for(int j=0;j<ServiceSelftestmodule.size();j++){
                BeanUtils.copyProperties(ServiceSelftestmodule.get(i),voServiceSelftestmoduleto3);
                voServiceSelftestmoduleto2.add(voServiceSelftestmoduleto3);
            }
        }
        return voServiceSelftestmoduleto2;
    }
}
