/**
 * @author sanguk on 2023/06/10
 */

package net.toyproject.mall.back.controller.common.model;

import lombok.Data;

@Data
public class ValidResponse {

    private final boolean success;
    private final String code;
    private final String message;

    public ValidResponse(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
