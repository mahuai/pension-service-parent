package com.lingyue.usercenter.feign.dto;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhanghengrui
 * @date 2022/6/30
 */
@Data
public class UserProfileDto implements Serializable {
    private static final long serialVersionUID = -8584538215909573568L;

    private String cdBrand;

    private String bcFrom;

    private Integer gender;

    private String gcid;

    private String mobile;

    private LocalDateTime updateTime;

    private String accountFrom;

    private Integer certifyMobileState;

    private String homeMarket;

    private String password;

    private LocalDateTime createTime;

    private Integer tokenVersion;

    private String salutation;

    private Integer state;

    private String cid;

    private String correspondLanguageISO;

    private String ucid;

}
