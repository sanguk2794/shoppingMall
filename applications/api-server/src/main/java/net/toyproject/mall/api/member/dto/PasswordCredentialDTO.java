/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member.dto;

import lombok.Data;
import net.toyproject.mall.common.code.MemberPlatform;

@Data
public class PasswordCredentialDTO {

    private String emailAddress;
    private String password;
    private MemberPlatform memberPlatform;

}
