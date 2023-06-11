
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

    public String firstName;

    public String lastName;

    public String accessToken;

    public Date accessTokenExpDt;


    public LoginUser(String username, String password, Long memberSn, String firstName, String lastName, String accessToken, Date accessTokenExpDt) {
        super(username, password, true, true, true, true, new ArrayList<>());
        this.memberSn = memberSn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessToken = accessToken;
        this.accessTokenExpDt = accessTokenExpDt;
    }

    public LoginUser(String username, String password, Long memberSn, String firstName, String lastName, String accessToken, Date accessTokenExpDt, List<GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
        this.memberSn = memberSn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessToken = accessToken;
        this.accessTokenExpDt = accessTokenExpDt;
    }
}