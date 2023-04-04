package com.lingyue.usercenter.feign.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListByAccountIdVo {


    @ApiModelProperty("用户id")
    private String accountId;

    @JsonProperty("VIN")
    @JSONField(name = "VIN")
    @ApiModelProperty("17位vin码")
    private String vin;

    @ApiModelProperty("厂商类型：0-BMW(不传时默认)；1-MINI")
    private Integer brandType;


}