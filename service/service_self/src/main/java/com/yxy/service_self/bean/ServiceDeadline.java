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
 * 自测截止审核时间表
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceDeadline对象", description="自测截止审核时间表")
public class ServiceDeadline implements Serializable {
//这个表的主键由学校名字、专业名字、年份构成，而mybatis的主键mp注册只能有一个，所以干脆不声明注释，虽然这样用不了selectbyid就用QueryWrapper好了
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;


    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。
    @ApiModelProperty(value = "自测截止时间")
    @TableField("studentDeadline")
    private Date studentDeadline;

    @ApiModelProperty(value = "自测审核截止时间")
    @TableField("managerDeadline")
    private Date managerDeadline;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
