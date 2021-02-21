package com.yxy.service_studyScore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.ServiceScoretype;
import com.yxy.service_studyScore.bean.front.initScopeType;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.mapper.ServiceScoretypeMapper;
import com.yxy.service_studyScore.service.ServiceScoretypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Service
public class ServiceScoretypeServiceImpl extends ServiceImpl<ServiceScoretypeMapper, ServiceScoretype> implements ServiceScoretypeService {

    @Override
    public  List<ServiceScoretype>isExistSchoolMajor(String school,String department) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",school);
        wrapper.eq("department",department);
        List<ServiceScoretype> list = this.list(wrapper);

      return list;
    }



    @Override
    public void addInitScopeType(initScopeType scopeType) throws zongceException {
        String school = scopeType.getSchoolName();
        String department = scopeType.getDepartment();
        Map<String, Float> scoretypes = scopeType.getScoretypes();
        Set<String> keys = scoretypes.keySet();


        //判断是否已经有分类存在了，存在就不能添加
        List<ServiceScoretype> existSchoolMajor = isExistSchoolMajor(school, department);
        if (existSchoolMajor != null){
        for (String key : keys) {
            for (ServiceScoretype temp : existSchoolMajor) {
                if (key.equals(temp.getScoreTypeName())) {
                    throw new zongceException("已设置了综测大类项目 不能再设置");
                }
            }
        }
    }
        for (String key : keys) {

            ServiceScoretype scoretypeBean = new ServiceScoretype();
            scoretypeBean.setSchoolName(school);
            scoretypeBean.setDepartment(department);
            scoretypeBean.setScoreTypeName(key);
            scoretypeBean.setScore(scoretypes.get(key));
            this.save(scoretypeBean);

        }
        }

    @Override
    public List<ServiceScoretype> getScopeTypeByAdmin(String schoolName, String department) throws zongceException {
        List<ServiceScoretype> list = isExistSchoolMajor(schoolName, department);
        if(list==null){
            throw new zongceException("找不到该院校属性");
        }
        return list;
    }

    @Override
    public boolean updateScoretype(String schoolName, String department, String changedItem,Float changerValue, String newItem,Float newValue) {
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("schoolName",schoolName);
        updateWrapper.eq("department",department);
        updateWrapper.eq("scoreTypeName",changedItem);
        updateWrapper.eq("score",changerValue);
        ServiceScoretype a=new ServiceScoretype();
        a.setScoreTypeName(newItem);
        a.setScore(newValue);
        a.setDepartment(department);
        a.setSchoolName(schoolName);
        return   this.update(a,updateWrapper);
    }

    @Override
    public boolean deleteScoreType(String schoolName, String department, String deleteItem) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        wrapper.eq("scoreTypeName",deleteItem);
        return this.remove(wrapper);
    }


}
