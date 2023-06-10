/**
 * @author sanguk on 2023/06/10
 */

package net.toyproject.mall.back.config.handler;

import net.toyproject.mall.back.controller.common.model.ValidResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BackofficeExceptionHandler {

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> validException(BindException exception) {
        return new ResponseEntity<>(
                toValidResponses(exception), HttpStatus.BAD_REQUEST);
    }

    private List<ValidResponse> toValidResponses(BindException bindException) {
        return bindException.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidResponse(
                        false,
                        error.getField(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
