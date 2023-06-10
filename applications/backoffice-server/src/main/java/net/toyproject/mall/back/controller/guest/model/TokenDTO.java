package net.toyproject.mall.back.controller.guest.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenDTO implements Serializable {

    private String responseCode;
    private Long memberSn;
    private String accessToken;
    private Date accessTokenExpDt;
    private String refreshToken;
    private Date refreshTokenExpDt;
    private String transferMemberAgreeToken;

}
