/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.config.filter;

import net.toyproject.mall.back.util.ConstUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResourceFilter implements Filter {

    private static final String RESOURCE_PATH = ConstUtils.RESOURCE_PATH;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();

        if (Pattern.compile(RESOURCE_PATH).matcher(path).find()) {
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
