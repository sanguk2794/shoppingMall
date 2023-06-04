/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.config.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Exception Handler
 * */
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(final WebRequest request, final Exception e) {
        final Map<String, Object> responseAttributes = new LinkedHashMap<>();
        responseAttributes.put("timestamp", new Date());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        responseAttributes.put("status", httpStatus.value());

        logger.error(e.getMessage(), e);

        return handleExceptionInternal(e, responseAttributes, new HttpHeaders(), httpStatus, request);
    }
}
