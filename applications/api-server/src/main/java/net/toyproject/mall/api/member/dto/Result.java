/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member.dto;

import lombok.Data;

@Data
public class Result {

    private boolean success = true;
    private String errorCode;
    private String errorMsg;
}
