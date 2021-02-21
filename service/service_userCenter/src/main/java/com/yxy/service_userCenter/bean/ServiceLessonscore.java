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
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceLessonscore对象", description="")
public class ServiceLessonscore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "userId")
    private String userId;

    @TableField("schoolName")
    private String schoolName;

    @TableField("department")
    private String department;



    @TableField("lessonTotalScore")
    private float lessonTotalScore;


    @TableField("year")
    private int year;

    @TableField("pictureUrl")
    private String pictureUrl;

    @TableField("pictureFileName")
    private String pictureFileName;


}
