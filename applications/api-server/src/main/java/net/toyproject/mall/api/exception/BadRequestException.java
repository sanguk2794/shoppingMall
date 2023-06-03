/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.exception;

import net.toyproject.mall.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * HTTP Status 400 exception.
 */
public class BadRequestException extends RestApiException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.InvalidParameter.getErrorMessage());
    }

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, ErrorCode.InvalidParameter.getErrorMessage(), message);
    }
}
