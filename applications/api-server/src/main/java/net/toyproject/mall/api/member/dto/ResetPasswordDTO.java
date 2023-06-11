/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.api.member.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {

    private Long memberSn;

    private String password;

}
