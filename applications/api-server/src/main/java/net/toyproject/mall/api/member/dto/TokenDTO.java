/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String accessToken;
    private String tokenType;
}
