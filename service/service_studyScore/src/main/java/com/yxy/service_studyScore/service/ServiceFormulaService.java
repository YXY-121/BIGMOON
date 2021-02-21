package com.yxy.service_studyScore.service;

import com.yxy.service_studyScore.bean.ServiceFormula;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.service_studyScore.bean.front.initFormulaVo;

import com.yxy.service_studyScore.exception.zongceException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
public interface ServiceFormulaService extends IService<ServiceFormula> {

    boolean isWrongAttribute(String formula,String school,String department);



    ServiceFormula isFormulaExist(initFormulaVo formula);

    ServiceFormula updateFormula(String schoolName, String department, String changedItem, String newValue);

    ServiceFormula getFormulaByAdmin(String schoolName, String department);

    boolean deleteFormula(String schoolName, String department);
}
