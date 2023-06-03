/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.member.service.impl;

import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.repository.MemberRepository;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public Member createMember(Member member) {
        return repository.save(member);
    }

    @Override
    public Member findMember(Long memberSn) {
        return repository.findById(memberSn).orElse(null);
    }

    @Override
    public Member updateMember(Member member) {
        return repository.save(member);
    }

    @Override
    public Member lockMember(Long memberSn, YN lockYn) {
        return null;
    }

    @Override
    public Member resetPassword(Long memberSn, String newPassword) {
        return null;
    }

    @Override
    public Member closeMember(Long memberSn) {
        return null;
    }

    @Override
    public boolean isLockMember(Long memberSn) {
        return false;
    }
}
