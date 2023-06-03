package net.toyproject.mall.api.exception;

import net.toyproject.mall.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * HTTP Status 404 exception.
 */
public class NotFoundException extends RestApiException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NotFount.getErrorMessage());
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, ErrorCode.NotFount.getErrorMessage());
    }
}
