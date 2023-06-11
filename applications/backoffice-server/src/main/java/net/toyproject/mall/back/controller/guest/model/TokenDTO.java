package net.toyproject.mall.back.controller.guest.model;

import lombok.Data;
import net.toyproject.mall.common.code.LoginProcessStatusCode;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenDTO implements Serializable {

    private LoginProcessStatusCode responseCode;
    private Long memberSn;
    public String firstName;

    public String lastName;
    private String accessToken;
    private Date accessTokenExpDt;
    private String transferMemberAgreeToken;

}
