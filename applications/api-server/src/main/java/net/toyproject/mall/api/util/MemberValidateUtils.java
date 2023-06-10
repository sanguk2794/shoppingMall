/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.util;

import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.api.member.dto.LoginParam;
import net.toyproject.mall.api.member.dto.RegisterMemberDTO;
import net.toyproject.mall.api.member.dto.UpdateMemberDTO;
import org.springframework.util.StringUtils;

public class MemberValidateUtils {

    private MemberValidateUtils() {
        throw new AssertionError("this class is utility class");
    }

    public static void registerMemberValidate(RegisterMemberDTO memberDTO) {
        if (!StringUtils.hasLength(memberDTO.getEmailAddress())) {
            throw new BadRequestException("emailAddress must not null");
        }

        if (!StringUtils.hasLength(memberDTO.getPassword())) {
            throw new BadRequestException("password must not null");
        }

        if (memberDTO.getMemberPlatform() == null) {
            throw new BadRequestException("platform must not null");
        }
    }
    public static void updateMemberValidate(UpdateMemberDTO memberDTO) {
        if (!StringUtils.hasLength(memberDTO.getPassword())) {
            throw new BadRequestException("password must not null");
        }
    }

    public static void loginMemberValidate(LoginParam loginParam) {
        if (!StringUtils.hasLength(loginParam.getEmailAddress())) {
            throw new BadRequestException("emailAddress must not null");
        }

        if (!StringUtils.hasLength(loginParam.getPassword())) {
            throw new BadRequestException("password must not null");
        }

        if (loginParam.getMemberPlatform() == null) {
            throw new BadRequestException("platform must not null");
        }
    }
}
