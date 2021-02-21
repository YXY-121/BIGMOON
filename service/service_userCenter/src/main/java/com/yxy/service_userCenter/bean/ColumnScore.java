package com.yxy.service_userCenter.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
public class ColumnScore {
    public String lessonAttribute;//第一步，后端负责获取课程属性值
    public List<Float> scores;//第二部
    public double sum;
}
