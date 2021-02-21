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
@EqualsAndHashCode(callSuper = false)////班干或管理员查询也可做返回辅助类
@Accessors(chain = true)//用于查询某位同学某个类型某个年份，所以这里不需要返回年份
@ApiModel(value="voServiceSelftestmoduleto1对象", description="自测模块查询自己返回辅助类")
public class voServiceSelftestmoduleto2 {
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

    @ApiModelProperty(value = "加分数",example="1.0")
    private Float score;

    @ApiModelProperty(value = "证明图片")
    @TableField("url")
    private String url;
}
