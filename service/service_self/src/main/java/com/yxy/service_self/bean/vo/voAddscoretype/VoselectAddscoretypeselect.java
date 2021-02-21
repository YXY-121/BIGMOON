package com.yxy.service_self.bean.vo.voAddscoretype;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//给学生用
@ApiModel(value="VoselectAddscoretypeselect", description="自测加分大类查询辅助类")
public class VoselectAddscoretypeselect {
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;


}
