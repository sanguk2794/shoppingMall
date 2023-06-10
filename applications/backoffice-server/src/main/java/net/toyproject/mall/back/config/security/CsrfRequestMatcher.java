/**
 * @author sanguk on 2023/06/04
 */

/*
package net.toyproject.mall.back.config.security;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CsrfRequestMatcher implements RequestMatcher {

    // Always allow the HTTP GET method
    private final Pattern allowedMethods = Pattern.compile("^GET$");
    private final Pattern requestURLMethods = Pattern.compile("./dashboard|guest|order|cs|logout/.");

    private final RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher(
            "/unprotected", null);

    @Override
    public boolean matches(HttpServletRequest request) {

        // Skip checking if request method is a GET
        if (allowedMethods.matcher(request.getMethod()).matches() ||
                !requestURLMethods.matcher(request.getRequestURL()).find()) {
            return false;
        }

        // Check CSRF in all other cases.
        return !unprotectedMatcher.matches(request);
    }
}
*/
