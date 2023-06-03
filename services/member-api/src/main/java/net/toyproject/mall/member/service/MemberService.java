package net.toyproject.mall.member.service;

import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.member.model.Member;

public interface MemberService {

    Member createMember(Member member);

    Member findMember(Long memberSn);

    Member updateMember(Member member);

    Member lockMember(Long memberSn, YN lockYn);

    Member resetPassword(Long memberSn, String newPassword);

    Member closeMember(Long memberSn);

    boolean isLockMember(Long memberSn);

}
