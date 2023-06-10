/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.toyproject.mall.common.code.LoginProcessStatusCode;

import java.util.Date;

@Data
public class TokenDTO {

    private LoginProcessStatusCode responseCode;
    private Long memberSn;
    private String accessToken;
    private Date accessTokenExpDt;
    private String transferMemberAgreeToken;

}
