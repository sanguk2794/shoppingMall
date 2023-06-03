package net.toyproject.mall.api.exception;

import net.toyproject.mall.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * HTTP Status 401 exception.
 */
public class UnauthorizedException extends RestApiException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.Unauthorized.getErrorMessage());
    }

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.Unauthorized.getErrorMessage(), message);
    }
}
