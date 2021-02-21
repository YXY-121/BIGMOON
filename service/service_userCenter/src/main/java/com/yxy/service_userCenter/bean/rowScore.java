package com.yxy.service_userCenter.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class rowScore {
private String lessonName;
private List<Map<String,Float>> scores;
 }
