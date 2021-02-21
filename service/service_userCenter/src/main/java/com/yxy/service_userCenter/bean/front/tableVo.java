package com.yxy.service_userCenter.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class tableVo {
  private  List<scoreTable> table;
  private  String userId;
  private String department;
  private String schoolName;
  private int year;
}
