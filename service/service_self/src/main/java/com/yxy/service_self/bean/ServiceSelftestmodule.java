package com.yxy.service_self.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自测模块表
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceSelftestmodule对象", description="自测模块表")
public class ServiceSelftestmodule implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自测模块Id")
    @TableId(value = "selfTestId", type = IdType.ID_WORKER_STR)
    private String selfTestId;

    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "学号Id")
    @TableField("userId")
    private String userId;

    @ApiModelProperty(value = "自测分数类型")
    @TableField("selfTestScoreType")
    private String selfTestScoreType;

    @ApiModelProperty(value = "项目名称")
    @TableField("projectName")
    private String projectName;

    @ApiModelProperty(value = "加分数")
    @TableField("score")
    private Float score;

    @ApiModelProperty(value = "证明图片")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
