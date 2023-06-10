/**
 * @author sanguk on 2023/06/10
 */

package net.toyproject.mall.back.config.filter;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CsrfRequestFilter extends OncePerRequestFilter {

    private static final String CSRF_COOKIE_NAME = "_ctkn";
    private static final String CSRF_COOKIE_PATH = "/";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        final CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (!Objects.isNull(csrf)) {
            Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
            final String token = csrf.getToken();

            if (invalid(cookie, token)) {
                cookie = new Cookie(CSRF_COOKIE_NAME, token);
                cookie.setPath(CSRF_COOKIE_PATH);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(20);
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(request, response);
    }

    private static boolean invalid(Cookie cookie, String token) {
        return cookie == null || token != null && !token.equals(cookie.getValue());
    }
}
