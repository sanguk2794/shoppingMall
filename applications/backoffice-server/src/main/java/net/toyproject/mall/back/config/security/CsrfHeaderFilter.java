/**
 * @author sanguk on 2023/06/04
 */
/*

package net.toyproject.mall.back.config.security;

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

public class CsrfHeaderFilter extends OncePerRequestFilter {

    private static final String CSRF_COOKIE_NAME = "_ctkn";
    private static final String CSRF_COOKIE_PATH = "/";

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    @NotNull HttpServletResponse res,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        final CsrfToken csrf = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
        if (!Objects.isNull(csrf)) {
            final Cookie cookie = WebUtils.getCookie(req, CSRF_COOKIE_NAME);
            final String token = csrf.getToken();
            if (invalidCsrf(cookie, token)) {
                addCookie(res, token);
            }
        }

        filterChain.doFilter(req, res);
    }

    private static boolean invalidCsrf(Cookie cookie, String token) {
        return cookie == null || token != null && !token.equals(cookie.getValue());
    }

    private static void addCookie(@NotNull HttpServletResponse res, String token) {
        final Cookie cookie = new Cookie(CSRF_COOKIE_NAME, token);
        cookie.setPath(CSRF_COOKIE_PATH);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(20);
        res.addCookie(cookie);
    }
}
*/
