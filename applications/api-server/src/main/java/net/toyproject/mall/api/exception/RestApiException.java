/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.exception;

import org.springframework.http.HttpStatus;

public class RestApiException extends RuntimeException {

    private final HttpStatus status;
    private final String errorCode;

    public RestApiException(HttpStatus status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public RestApiException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}
