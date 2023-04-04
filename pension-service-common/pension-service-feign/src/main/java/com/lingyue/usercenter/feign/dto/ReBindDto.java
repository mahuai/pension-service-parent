package com.lingyue.usercenter.feign.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReBindDto {
    /**
     * rnrUniqueId
     */
    @NotBlank
    private String rnrUniqueId;

    /**
     * echChange记录Id
     */
    @NotBlank
    private Long historyId;
}
