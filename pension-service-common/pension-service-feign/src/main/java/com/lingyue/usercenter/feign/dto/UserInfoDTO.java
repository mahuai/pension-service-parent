package com.lingyue.usercenter.feign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * userservice/profile
 * <p/>
 *
 * @author cuiwei
 * @date 2022/7/13 9:24
 */
@Getter
@Setter
public class UserInfoDTO {

    private String surname;
    private String givenName;
    private String gender;
    private String mobile;
    private String cid;
    private String gcid;
    private String ucid;
    private String appleNickName;
    private String wxNickName;
    private String baseNickName;
    private String appleHeadImgUrl;
    private String wxHeadImgUrl;
    private String baseHeadImgUrl;
    private String backGroundImgUrl;
    private Boolean wxIsBind;
    private Boolean appleIsBind;
    private String birth;
    private String unionId;
    private String appleId;
}
