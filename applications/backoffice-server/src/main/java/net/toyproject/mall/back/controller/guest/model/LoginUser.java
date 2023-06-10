
package net.toyproject.mall.back.controller.guest.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoginUser extends User {

    private static final long serialVersionUID = 1L;

    public Long memberSn;

    public String accessToken;

    public String refreshToken;

    public Date accessTokenExpDt;

    public Date refreshTokenExpDt;

    public LoginUser(String username, String password, Long memberSn, String accessToken, String refreshToken, Date accessTokenExpDt, Date refreshTokenExpDt) {
        super(username, password, true, true, true, true, new ArrayList<>());
        this.memberSn = memberSn;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpDt = accessTokenExpDt;
        this.refreshTokenExpDt = refreshTokenExpDt;
    }

    public LoginUser(String username, String password, Long memberSn, String accessToken, String refreshToken, Date accessTokenExpDt, Date refreshTokenExpDt, List<GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
        this.memberSn = memberSn;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpDt = accessTokenExpDt;
        this.refreshTokenExpDt = refreshTokenExpDt;
    }
}