package net.toyproject.mall.api.config.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * print request parameter
 */
public class DebugParamInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (logger.isDebugEnabled()) {
                printParams(request);
            }

        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn(e.getMessage(), e);
            }
        }

        return true;
    }

    private void printParams(HttpServletRequest request) {
        final StringBuilder buffer = new StringBuilder();
        printHeader(request, buffer);
        printParameter(request, buffer);
        printRequestBody(request, buffer);
        buffer.append("==================================================================");

        logger.debug("\n{}", buffer);
    }

    private static void printHeader(HttpServletRequest request, StringBuilder buffer) {
        buffer.append("==================================================================\n");
        buffer.append(request.getScheme());
        buffer.append(", ");
        buffer.append(request.getMethod());
        buffer.append(", ");
        buffer.append(request.getRequestURI());
        buffer.append("\n");
    }

    private static void printParameter(HttpServletRequest request, StringBuilder buffer) {
        final Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            final String paramName = paramNames.nextElement();
            final String[] paramValues = request.getParameterValues(paramName);

            buffer.append(getParamBuffer(paramName, paramValues));
        }
    }

    private static StringBuilder getParamBuffer(String paramName, String[] paramValues) {
        StringBuilder buffer = new StringBuilder();
        if (paramValues == null || paramValues.length == 0) {
            buffer.append(paramName);
            buffer.append(" : paramValues is null");

        } else if (paramValues.length == 1) {
            buffer.append(paramName);
            buffer.append(" : ");
            buffer.append(paramValues[0]);

        } else {
            for (String paramValue : paramValues) {
                buffer.append(paramName);
                buffer.append(" : ");
                buffer.append(paramValue);
                buffer.append("\n");
            }
        }

        buffer.append("\n");

        return buffer;
    }

    private void printRequestBody(HttpServletRequest request, StringBuilder buffer) {
        try {
            String requestBody = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
            if (StringUtils.hasLength(requestBody)) {
                buffer.append("requestBody : \n");
                buffer.append(getRequestBodyBuffer(requestBody));
                buffer.append("\n");
            }

        } catch (IOException ignored) {
        }
    }

    private StringBuilder getRequestBodyBuffer(String requestBody) {
        StringBuilder buffer = new StringBuilder();
        try {
            setResponseBodyBuffer(requestBody, buffer);

        } catch (Exception e) {
            buffer.append(requestBody);
        }

        return buffer;
    }

    private void setResponseBodyBuffer(String requestBody, StringBuilder buffer) throws JsonProcessingException {
        if (requestBody.startsWith("{")) {
            Map<?, ?> map = OBJECT_MAPPER.readValue(requestBody, Map.class);
            buffer.append(map == null ? requestBody : toJson(map));

        } else if (requestBody.startsWith("[")) {
            List<?> list = OBJECT_MAPPER.readValue(requestBody, List.class);
            buffer.append(list == null ? requestBody : toJson(list));

        } else {
            buffer.append(requestBody);
        }
    }

    private String toJson(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writer().withDefaultPrettyPrinter().writeValueAsString(object);
    }
}
