/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    Unauthorized("Authentication Error"),
    Forbidden("Not Authorized"),
    NotFount("Resource Not Found"),
    InvalidParameter("Invalid Input Parameter");

    private final String errorMessage;

}
