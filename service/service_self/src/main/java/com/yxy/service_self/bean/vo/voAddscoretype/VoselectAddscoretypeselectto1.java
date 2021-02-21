package com.yxy.service_self.bean.vo.voAddscoretype;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//管理员用
@ApiModel(value="VoselectAddscoretypeselectto1", description="自测加分大类返回有年份辅助类")
public class VoselectAddscoretypeselectto1 {
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "自测分数类型")
    @TableField("selfTestScoreType")
    private String selfTestScoreType;

    @ApiModelProperty(value = "分数比例")
    private Float proportion;

    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。
  /*  @ApiModelProperty(value = "自测加分大类Id")
    @TableId(value = "addscoretypeId", type = IdType.ASSIGN_ID)
    private String addscoretypeId;*/
}
