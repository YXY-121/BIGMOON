package com.yxy.service_self.bean.vo.voServiceSelftestmodule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="voServiceSelftestmoduleupdate对象", description="自测更新辅助类")
public class voServiceSelftestmoduleupdate {
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

    @ApiModelProperty(value = "加分数",example="1.0")
    private Float score;

    @ApiModelProperty(value = "证明图片")
    @TableField("url")
    private String url;//这里的路径是更新后的

    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。
}
