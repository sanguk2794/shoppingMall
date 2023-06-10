/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.config.handler;

import net.toyproject.mall.back.util.ConstUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * ログイン成功後のカスタム処理
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (response.isCommitted()) {
            return;
        }

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String redirectUrl = ConstUtils.DEFAULT_REDIRECT_URL;
        if (!Objects.isNull(savedRequest) && StringUtils.isNotEmpty(savedRequest.getRedirectUrl())) {
            if (Pattern.compile(".*/|shop|prod|mypage|order|cs|/").matcher(savedRequest.getRedirectUrl()).find()) {
                redirectUrl = savedRequest.getRedirectUrl();
            }
        }

        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }
}
