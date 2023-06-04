/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.util;

import net.toyproject.mall.api.member.dto.RegisterMemberDTO;
import net.toyproject.mall.api.member.dto.UpdateMemberDTO;
import net.toyproject.mall.common.code.MemberStatus;
import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.member.model.Member;

public class MemberUtils {

    private MemberUtils() {
        throw new AssertionError("this class is utility class");
    }

    public static Member registerToMember(RegisterMemberDTO memberDTO) {
        Member member = new Member();
        member.setEmailAddress(memberDTO.getEmailAddress());
        member.setAddress(memberDTO.getAddress());
        member.setPassword(memberDTO.getPassword());
        member.setNickName(memberDTO.getNickName());
        member.setName(memberDTO.getName());
        member.setAddress(memberDTO.getAddress());
        member.setMemberStatus(MemberStatus.Normal);
        member.setMemberPlatform(memberDTO.getMemberPlatform());
        member.setLockYN(YN.N);
        member.setPasswordFailureCount(0);

        return member;
    }

    public static Member updateToMember(UpdateMemberDTO memberDTO, Member member) {
        member.setAddress(memberDTO.getAddress());
        member.setPassword(memberDTO.getPassword());
        member.setNickName(memberDTO.getNickName());
        member.setName(memberDTO.getName());
        member.setAddress(memberDTO.getAddress());
        member.setLockYN(YN.N);
        member.setPasswordFailureCount(0);

        return member;
    }
}
