/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.config.interceptor;

import net.toyproject.mall.back.util.ConstUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class CheckInterceptor implements HandlerInterceptor {

    private static final String RESOURCE_PATH = ConstUtils.RESOURCE_PATH;
    private static final String ERROR_PATH = ConstUtils.ERROR_PATH;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {

        if (Pattern.compile(RESOURCE_PATH).matcher(request.getRequestURL()).find() || Pattern.compile(ERROR_PATH).matcher(request.getRequestURL()).find()) {
            return Boolean.TRUE;
        }

        return Boolean.TRUE;
    }
}
