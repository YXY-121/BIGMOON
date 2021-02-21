package com.yxy.service_self.bean.vo.voServiceSelftestmodule;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="voServiceSelftestmoduleList对象", description="自测模块表")
public class voServiceSelftestmoduleList {
    @ApiModelProperty(value = "voServiceSelftestmoduleList对象",example="{voServiceSelftestmodule,voServiceSelftestmodule}")
    @TableField("voServiceSelftestmoduleList")
    private List<voServiceSelftestmodule> voServiceSelftestmodule;
}
