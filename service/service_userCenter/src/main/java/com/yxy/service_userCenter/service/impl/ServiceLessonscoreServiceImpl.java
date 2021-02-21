package com.yxy.service_userCenter.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxy.service_studyScore.bean.ServiceFormula;
import com.yxy.service_studyScore.bean.ServiceLesson;
import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.exception.zongceException;
import com.yxy.service_studyScore.service.ServiceFormulaService;
import com.yxy.service_studyScore.service.ServiceLessonService;
import com.yxy.service_studyScore.service.ServiceLessonattributeService;
import com.yxy.service_userCenter.bean.*;
import com.yxy.service_userCenter.bean.front.lessonScoreVo;
import com.yxy.service_userCenter.bean.front.scoreTable;
import com.yxy.service_userCenter.mapper.ServiceLessonscoreMapper;
import com.yxy.service_userCenter.service.ServiceLessonscoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.service_userCenter.service.ServiceTotalscoreService;
import com.yxy.service_userCenter.service.ServiceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2020-12-12
 */
@Service
public class ServiceLessonscoreServiceImpl extends ServiceImpl<ServiceLessonscoreMapper, ServiceLessonscore> implements ServiceLessonscoreService {
    @Autowired
    ServiceUserService serviceUserService;
    @Autowired
    ServiceTotalscoreService totalscoreService;

    @Reference(parameters = { "protocol", "dubbo" })
    ServiceLessonattributeService lessonattributeService;

    @Reference(parameters = { "protocol", "dubbo" })
    ServiceLessonService lessonService;

    @Reference(parameters = { "protocol", "dubbo" })
    ServiceFormulaService formulaService;




    @Override
    public void saveScore(String school, String department, String userId, int year,float totalStudyScore) {

            ServiceLessonscore lessonscore=new ServiceLessonscore();
            //设置分数
            lessonscore.setLessonTotalScore(totalStudyScore);//score
            lessonscore.setSchoolName(school);
            lessonscore.setUserId(userId);
            lessonscore.setYear(year);
            lessonscore.setDepartment(department);
            this.save(lessonscore);
            //保存


    }

    @Override
    public void savePicture(String school, String userId, int year,List<String> file) {
        //再输入成绩后再插入图片的所以只能是update 不能直接insert进去
        String url=file.get(0);
        String fileName= file.get(1);

        UpdateWrapper update=new UpdateWrapper();
        update.eq("schoolName",school);
        update.eq("userId",userId);
        update.eq("year",year);
        update.set("pictureUrl",url);
        update.set("pictureFileName",fileName);
        this.update(update);
    }

    @Override
    public boolean isExistScore(String school, String userId, int year) {
        QueryWrapper wrapper=new QueryWrapper();

            wrapper.eq("schoolName",school);
            wrapper.eq("userId",userId);
            wrapper.eq("year",year);

            if(!this.list(wrapper).isEmpty())
                return true;

        return false;
    }

    @Override
    public void updateScore(String school, String department, String userId, int year, float totalStudyScore) {
        UpdateWrapper wrapper=new UpdateWrapper();
        wrapper.eq("schoolName",school);
        wrapper.eq("userId",userId);
        wrapper.eq("year",year);
        wrapper.set("lessonTotalScore",totalStudyScore);
        this.update(wrapper);

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
    public List<String> getLessonAttribute(String schoolName, String department) throws zongceException {
        List<ServiceLessonattribute> a = lessonattributeService.getAttributeByAdmin(schoolName, department);
        List<String> list =new ArrayList<>();
        for(ServiceLessonattribute temp:a){
            list.add(temp.getAttributeName());
        }

        return list;
    }

    //获取课程名称
    @Override
    public List<String> getLessonName(String schoolName, String department, String major, int year) throws zongceException {
        List<ServiceLesson>a=lessonService.getLessonByAdmin(schoolName,department);
        if(a.isEmpty()){
            throw new zongceException("你没课程");
        }
        List<String> list =new ArrayList<>();
        for(ServiceLesson temp:a){
            list.add(temp.getLessonName());
        }
        return list;
    }
    //计算rowScores里的每一列的总分
    public List<ColumnScore>  sumRowScores(List<ColumnScore> columnScores){
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



    //处理公式并且将sum填充进去
    @Override
    public String dealFormula(String formula,List<ColumnScore> columnScores){
        //将（学业+绩点*0.3）替换成（75+45*0.3）
        String newFormula=new String(formula);
        //varyFormula是用来存公式里的变量们
        List<String> varyFormula = splitFormula(formula);

        //开始将变量们用sum来替换
        for(String s:varyFormula){
            for (int i = 0; i< columnScores.size(); i++){

                if(s.equals(columnScores.get(i).getLessonAttribute())){
                    newFormula = newFormula.replaceAll(s, columnScores.get(i).getSum() + "");
                }
            }

        }
        return newFormula;
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

    public  Float calc(String newFormula) {
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
                        System.out.println(calcString);
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



    /**详细计算过程**/
    private static float calcProc(String calcString) {
//		if(calcString.contains("=")){
//			calcString = calcString.split("=")[1];
//		}
        //calcString = calcString.replace("(", "");
        //calcString = calcString.replace(")", "");

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
        System.out.println("result:" + result);
        return result;
    }



}
