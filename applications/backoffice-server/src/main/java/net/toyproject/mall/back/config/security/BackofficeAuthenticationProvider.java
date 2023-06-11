/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.config.security;

import net.toyproject.mall.back.controller.guest.model.LoginUser;
import net.toyproject.mall.back.controller.guest.model.TokenDTO;
import net.toyproject.mall.back.controller.guest.model.param.LoginParam;
import net.toyproject.mall.back.util.ConstUtils;
import net.toyproject.mall.common.code.LoginProcessStatusCode;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.restapi.client.member.LoginApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BackofficeAuthenticationProvider implements AuthenticationProvider {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    @Autowired
    private LoginApi loginApi;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final String id = auth.getName();
        final String password = auth.getCredentials().toString();
        if (StringUtils.isBlank(id) || StringUtils.isBlank(password)) {
            throw new AuthenticationCredentialsNotFoundException(LoginProcessStatusCode.Empty.name());
        }

        final TokenDTO tokensResult = loginApi.login(getLoginParam(id, password));

        if (LoginProcessStatusCode.Success != tokensResult.getResponseCode()) {
            throw new AuthenticationCredentialsNotFoundException(tokensResult.getResponseCode().name());

        } else {
            final List<GrantedAuthority> authUser = AuthorityUtils.createAuthorityList(DEFAULT_ROLE);
            return new UsernamePasswordAuthenticationToken(
                    new LoginUser(
                            ConstUtils.USER_DUMMY_ID,
                            ConstUtils.USER_DUMMY_PW,
                            tokensResult.getMemberSn(),
                            tokensResult.getFirstName(),
                            tokensResult.getLastName(),
                            tokensResult.getAccessToken(),
                            tokensResult.getAccessTokenExpDt()), ConstUtils.USER_DUMMY_PW, authUser);
        }
    }

    private static LoginParam getLoginParam(String id, String password) {
        final LoginParam param = new LoginParam();
        param.setEmailAddress(id);
        param.setPassword(password);
        param.setMemberPlatform(MemberPlatform.BackOffice);

        return param;
    }

    @Override
    public boolean supports(Class<?> token) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(token);
    }
}
