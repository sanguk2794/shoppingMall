package net.toyproject.mall.member.service;

import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.member.model.Member;

import java.util.List;

public interface MemberService {

    Member createMember(Member member);

    Member findMember(Long memberSn);

    Integer getMembersCount();

    List<Member> findMembers(int offset, int limit, OrderBy orderBy);

    Member findMemberByEmailAddress(String emailAddress);

    Member updateMember(Member member);

    long lockMember(Long memberSn, YN lockYn);

    Member resetPassword(Long memberSn, String newPassword);

    Member closeMember(Long memberSn);

    boolean isLockMember(Long memberSn);

    long increasePasswordVerifyFailureCnt(Long memberSn, Integer passwordFailureCount);

    boolean updatePassword(Long memberSn, String memberPassword);
}
