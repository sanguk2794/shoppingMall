/**
 * @author sanguk on 2023/06/10
 */

package net.toyproject.mall.api.config.handler;

import net.toyproject.mall.api.exception.RestApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler({RestApiException.class})
    public ResponseEntity<?> restApiExceptionHandler(RestApiException exception) {
        return new ResponseEntity<>(exception.getStatus());
    }
}
