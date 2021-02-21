package com.yxy.service_userCenter.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.ServiceFormula;
import com.yxy.service_studyScore.bean.ServiceLesson;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_userCenter.bean.excel.UserExcel;
import com.yxy.service_userCenter.bean.excel.UserExcelListener;
import com.yxy.service_studyScore.service.ServiceFormulaService;
import com.yxy.service_studyScore.service.ServiceLessonService;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import com.yxy.service_userCenter.bean.Myqueue;
import com.yxy.service_userCenter.bean.ServiceUser;
import com.yxy.service_userCenter.bean.ColumnScore;
import com.yxy.service_userCenter.mapper.ServiceUserMapper;
import com.yxy.service_userCenter.service.ServiceUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.service_userCenter.sms.RandomUtil;
import com.yxy.service_userCenter.sms.smsService;


import com.zongce.serviceBase.exception.zongceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-11
 */
@Service
public class ServiceUserServiceImpl extends ServiceImpl<ServiceUserMapper, ServiceUser> implements ServiceUserService {
    @Reference(parameters = { "protocol", "dubbo" })
    ServiceLessonattributeService lessonattributeService;
    @Reference(parameters = { "protocol", "dubbo" })
    ServiceLessonService lessonService;
    @Reference(parameters = { "protocol", "dubbo" })
    ServiceFormulaService formulaService;
    @Autowired
    smsService smsService;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    RandomUtil randomUtil;
    @Override
    public ColumnScore sumScore(ColumnScore columnScore) {
        List<Float> scores = columnScore.getScores();
        float sum = 0;
        for (float score : scores) {
            sum = sum + score;
        }
        columnScore.setSum(sum);
        return columnScore;
    }

    @Override
    public List<ColumnScore> sumScores(List<ColumnScore> columnScores) {
        for (ColumnScore row : columnScores) {
            List<Float> scores = row.getScores();
            float sum = 0;
            for (float score : scores) {
                sum += score;
            }
            row.setSum(sum);
        }
        return columnScores;
    }

    @Override
    public String GetFormula(String schoolName,String department) throws zongceException {
        //从数据库里获取公式
        ServiceFormula formulaByAdmin = formulaService.getFormulaByAdmin(schoolName, department);
        if(formulaByAdmin==null){
            throw new zongceException("没公式");
        }
        return formulaByAdmin.getFormula();

    }

    //获取课程属性
    @Override
    public List<String> getLessonAttribute(String schoolName, String department) throws zongceException, com.yxy.service_studyScore.exception.zongceException {
        List<ServiceLessonattribute> a = lessonattributeService.getAttributeByAdmin(schoolName, department);
        List<String> list =new ArrayList<>();
        for(ServiceLessonattribute temp:a){
            list.add(temp.getAttributeName());
        }

        return list;
    }

    //获取课程名称
    @Override
    public List<String> getLessonName(String schoolName, String department, String major, String year) throws zongceException {
        List<ServiceLesson>a=lessonService.getLessonByUser(schoolName,department,major,year);
        if(a.isEmpty()){
            throw new zongceException("你没课程");
        }
        List<String> list =new ArrayList<>();
        for(ServiceLesson temp:a){
            list.add(temp.getLessonName());
        }
        return list;
    }

    @Override
    public boolean isExistStudent(String schoolName, String userId) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("userId",userId);
        if(this.getOne(wrapper)!=null)
            return true;
        return false;
    }

    @Override
    public boolean updateSelfPwd(String schoolName, String userId, String pwd) {
        UpdateWrapper wrapper=new UpdateWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("userId",userId);
        wrapper.set("password",pwd);
        boolean update = this.update(wrapper);
        return update;
    }
    @Override
    public boolean sendCode(String phone)  {
        String fourCode = RandomUtil.getFourBitRandom();
        boolean b = false;
        try {
            b = smsService.sendSms(phone,fourCode);
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
        if(b){//发送成功
            redisTemplate.opsForValue().set(phone,fourCode);
            return true;
        }
        return false;

    }

    @Override
    public String getPhone(String schoolName, String userId) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("userId",userId);
        ServiceUser one = this.getOne(wrapper);
        return one.getPhone() ;
    }

    @Override
    public  boolean checkCode(String phone,String code){
        String s = redisTemplate.opsForValue().get(phone);
        if(s.equals(code)){
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceUser> getUserMegByAdmin(String schoolName, String department) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",schoolName);
        wrapper.eq("department",department);
        return this.list(wrapper);
    }

    @Override
    public boolean updateUserMeg(String schoolName, String department, String changedItem, String newValue) {
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("schoolName",schoolName);
        updateWrapper.eq("department",department);
        updateWrapper.set(changedItem,newValue);
        return this.update(updateWrapper);
    }

    @Override
    public void uploadUserFile(String schoolName, String pref, MultipartFile file) {
        InputStream in = null;
        try {
           in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EasyExcel.read(in, UserExcel.class, new UserExcelListener(this,schoolName,pref)).sheet().doRead();


    }

    public String useFormula(String formula ) {
        char[] formulaChars = formula.toCharArray();
      List list= splitFormula(formula);
        System.out.println(splitFormula(formula));
/*        for (int i = 0; i < formulaChars.length; i++) {
            char temp = formulaChars[i];
            //查找中文
            if ((temp != '+') && (temp != '-') && (temp != '=') && (temp != '*') && (temp != '/') && (temp != '%') && (temp != '^') && (temp != '(') && (temp != ')') && !('0' <= temp && temp <= '9') && (temp != '.') && (temp != '（') && (temp != '）')) {
                System.out.println(temp);

            }

            //一旦计算的时候就创建一个临时表 用完就删除？ 表名是学校专业 属性是公式上的属性。（中文） 然后就直接执行公式
        }*/
        return null;
    }
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

   public  float calc(String newFormula) {
        boolean stillHaveCalcSymbol = false;
        do{
            //System.out.println("before:" + newFormula);
            /** 寻找最后一个左括号里面到第一个右括号里面1的内容 **/
            char formulaArray[] = newFormula.toCharArray();
            for (int i = 0; i < formulaArray.length; i++) {
                if (formulaArray[i] == '+' || formulaArray[i] == '-'
                        || formulaArray[i] == '*' || formulaArray[i] == '/'
                        || formulaArray[i] == '(' || formulaArray[i] == ')') {
                    stillHaveCalcSymbol = true;
                } else {
                    stillHaveCalcSymbol = false;
                }
            }
            if (stillHaveCalcSymbol) {
                String resultFormula = "";
                //找最内层的括号里面的内容出来（含括号）
                for (int i = 0; i < formulaArray.length; i++) {
                    if (formulaArray[i] == ')') {
                        int begin = 0;
                        //回溯找到左括号(
                        for (int j = i; j >= 0; j--) {
                            if (formulaArray[j] == '(') {
                                begin = j;
                                break;
                            }
                        }
                        //subString获取括号里的字符串()
                        String calcString = newFormula.substring(begin, i + 1);
                        //resultFormula是括号里计算得到的一个数字
                        resultFormula = newFormula.replace(calcString, calcProc(calcString) + "");
                        //System.out.println(calcString);
                        break;
                    }
                }
                newFormula = resultFormula;
            }
        } while(stillHaveCalcSymbol);
        //最后得到普通的顺序无括号公式：
        System.out.println(newFormula);
        //最后一次计算:
     //   float result = calcProc("(" + newFormula.split("=")[1] + ")");
        return Float.valueOf(newFormula);
    }

    @Override
    public String insteadFormula(List<ColumnScore> columnScores, String formula) {
        String newFormula=new String(formula);
       List<String> list= splitFormula(newFormula);
        for(String s:list){
            for (int i = 0; i< columnScores.size(); i++){

                if(s.equals(columnScores.get(i).getLessonAttribute())){
                    newFormula = newFormula.replaceAll(s, columnScores.get(i).getSum() + "");
                }
            }

        }
        return newFormula;
    }


    /**详细计算过程**/
    private static float calcProc(String calcString) {


        String calcSymbol[] = {"\\*", "\\/", "\\+", "\\-"};
        char calcSymbolChar[] = {'*', '/', '+', '-'};
        boolean haveSymbol = true;
        float result = 0f;
        while(haveSymbol){
            System.out.println("calcStr:" + calcString);
            char calcCharArr[] = calcString.toCharArray();
            result = 0f;
            for (int i = 0; i < calcSymbol.length; i++) {
                boolean alreadyFind = false;
                for(int j = 0; j < calcCharArr.length; j++){
                    if(calcCharArr[j] == calcSymbolChar[i]){
                        //System.out.println("找到了" + calcSymbolChar[i]);
                        //以符号为中心，以左右两边的其他符号为边界找到两边的数
                        float num1 = 0f;
                        float num2 = 0f;
                        int bottom = 0;
                        //找到左边的数
                        for(int k = j - 1; k >= 0 && (calcCharArr[k] >= '0' && calcCharArr[k] <= '9' || calcCharArr[k] == '.') ; k--){
                            //System.out.println(calcCharArr[k] + "");
                            bottom = k;
                        }
                        //System.out.println("[j, bottom]:" + String.format("[%d, %d]", j, bottom));
                        //用取子字串的方法获得该符号左边的数，
                        num1 = Float.valueOf(calcString.substring(bottom, j));
                        System.out.println("num1:" + num1);
                        int top = 0;
                        //找到右边的数
                        for(int k = j + 1; k < calcString.length() && (calcCharArr[k] >= '0' && calcCharArr[k] <= '9' || calcCharArr[k] == '.'); k++){
                            top = k;
                        }
                        num2 = Float.valueOf(calcString.substring(j + 1, top + 1));
                        System.out.println("num2:" + num2);
                        //计算result=该括号里的计算总值
                        switch(calcSymbolChar[i]){
                            case '*':
                                result = num1 * num2;
                                break;
                            case '/':
                                result = num1 / num2;
                                break;
                            case '+':
                                result = num1 + num2;
                                break;
                            case '-':
                                result = num1 - num2;
                                break;
                        }
                        //System.out.println("bottom to top:" + calcString.substring(bottom + 1, top + 1));
                        calcString = calcString.replace(calcString.substring(bottom, top + 1), String.format("%.5f", result));
                        //System.out.println("end_calcStr:" + calcString);
                        alreadyFind = true;
                        break;
                    }
                }
                if(alreadyFind) break;
            }
            haveSymbol = false;
            if(calcString.contains("*") || calcString.contains("/") || calcString.contains("+") || calcString.contains("-")){
                haveSymbol = true;
                //System.out.println("找到");
            } else {
                //System.out.println("找不到");
            }
        }
        //System.out.println("result:" + result);
        return result;
    }



}