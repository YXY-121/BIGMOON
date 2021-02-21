package com.yxy.service_studyScore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.Myqueue;
import com.yxy.service_studyScore.bean.ServiceFormula;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.front.initFormulaVo;

import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.mapper.ServiceFormulaMapper;
import com.yxy.service_studyScore.service.ServiceFormulaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Service
@com.alibaba.dubbo.config.annotation.Service

public class ServiceFormulaServiceImpl extends ServiceImpl<ServiceFormulaMapper, ServiceFormula> implements ServiceFormulaService {

   @Autowired
    ServiceLessonattributeService lessonAttribute;
    @Override//根据课程属性查出来的属性，核对公式是否正确
    public boolean isWrongAttribute(String formula,String school,String department)  {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",school);
        wrapper.eq("department",department);
        List<ServiceLessonattribute>  serviceLessonattribute = lessonAttribute.list(wrapper);
        System.out.println(serviceLessonattribute);

        List<String> splitFormula = splitFormula(formula);
        System.out.println(splitFormula);
        boolean flag=true;

        for(String f:splitFormula){
            for(ServiceLessonattribute a:serviceLessonattribute){
                if(!f.equals(a.getAttributeName()))
                    flag=false;
            }

    }
        return flag;

    }
    @Override
    public ServiceFormula isFormulaExist(initFormulaVo formula) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",formula.getSchoolName());
        wrapper.eq("department",formula.getDepartment());
        ServiceFormula one =this.getOne(wrapper);
       return one;
    }

    @Override
    public ServiceFormula updateFormula( String schoolName, String department,String changedItem, String newValue) {

        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("schoolName",schoolName);
        updateWrapper.eq("department",department);
        updateWrapper.eq("formula",changedItem);
        ServiceFormula a=new ServiceFormula();
        a.setDepartment(department);
        a.setSchoolName(schoolName);
        a.setFormula(newValue);
        this.update(a,updateWrapper);



            boolean update;
        update = this.update(a,updateWrapper);

        if (update) {
            return getformula(schoolName, department);
        }

        return null;
    }

    @Override
    public ServiceFormula getFormulaByAdmin(String schoolName, String department) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        return this.getOne(wrapper);

    }

    @Override
    public boolean deleteFormula(String schoolName, String department) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
       return this.remove(wrapper);

    }

    //其分割出公式里变量名字
    public static List splitFormula(String formula) {
        List zimu= new LinkedList();
        Myqueue q=new Myqueue(100);//需要改进
        char[] word = new char[formula.length()];
        formula.getChars(0, formula.length(), word, 0);
        for(int i=0;i<word.length;i++)
        {
            char temp = word[i];
            StringBuffer bf=new StringBuffer();
            if ((temp == '+')|| (temp == '-') ||(temp == '=' )||( temp == '*') ||(temp == '/') || (temp == '%') || (temp == '^') || (temp == '(') || (temp == ')')||('0'<=temp&&temp<='9')||(temp == '.') || (temp == '（') || (temp == '）'))
            {
                Boolean flag=false;
                while(q.isEmpty()==false)//并不是一个个压入的 是在i=某个数字时，全部将其压入。所以你也不知道最后一个属性的字符串压入时。i是多少
                {
                    Object object=q.poll();
                    bf.append(object);
                    flag=true;
                }
                if(flag)
                    zimu.add(bf.toString());
            }
            else if (i==word.length-1)//结尾不是特殊符号 2*学分 也能得到‘学分’这个属性
            {
                while(q.isEmpty()==false){
                    bf.append(q.poll());
                }
                bf.append(temp);//这里是必要的，因为当是最后一个是它没有被压入队列，因为由于else if直接跳到这里错过了q.add(temp)，所以这里追加上它

                zimu.add(bf.toString());
            }
            else
            {
                q.add(temp);//q里为空时 说明
            }
        }
        return zimu;
    }
    public ServiceFormula getformula(String school,String department){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",school);
        wrapper.eq("department",department);
        return this.getOne(wrapper);
    }
}
