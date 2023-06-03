/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.exception;

import net.toyproject.mall.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * HTTP Status 403 exception
 */
public class ForbiddenException extends RestApiException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, ErrorCode.Forbidden.getErrorMessage());
    }

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, ErrorCode.Forbidden.getErrorMessage(), message);
    }

}
