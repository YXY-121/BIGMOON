package com.yxy.service_self.bean.vo.voAddscoretype;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//管理员用
@ApiModel(value="VoselectAddscoretypeselectto", description="自测加分大类返回无年份辅助类")
public class VoselectAddscoretypeselectto {
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
   /* @ApiModelProperty(value = "自测加分大类Id")
    @TableId(value = "addscoretypeId", type = IdType.ASSIGN_ID)
    private String addscoretypeId;*/


}
