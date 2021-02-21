package com.yxy.service_self.bean.vo.voAddscoretype;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//初始化模块全部删除，如果觉得某个学校某个院哪条记录不对去修改就好了
@ApiModel(value="VoAddscoretypedelete", description="自测加分大类初始化删除辅助类")
public class VoAddscoretypedelete {
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "年份",example = "2019")
    private String year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year
}
