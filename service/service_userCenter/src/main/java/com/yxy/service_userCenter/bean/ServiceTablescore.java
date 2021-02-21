package com.yxy.service_userCenter.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yxy
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceTablescore对象", description="")
public class ServiceTablescore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("lessonName")
    private String lessonName;

    @TableField("userId")
    private String userId;

    @TableField("attributeName")
    private String attributeName;


    @TableField("score")
    private  Float score;

    @TableField("year")
    private int year;



}
