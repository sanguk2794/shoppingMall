/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
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
