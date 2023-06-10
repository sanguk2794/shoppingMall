/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.config.handler;

import net.toyproject.mall.back.util.ConstUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BackOfficeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException authenticationException) throws IOException {

        String errorCodeUrl = "";
        if (StringUtils.isNotBlank(authenticationException.getMessage())) {
            errorCodeUrl = "?error=" + authenticationException.getMessage();
        }

        httpServletResponse.sendRedirect(
                httpServletRequest.getContextPath() + ConstUtils.LOGIN_URL + errorCodeUrl);
    }
}
