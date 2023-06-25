/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.member.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.model.QMember;
import net.toyproject.mall.member.repository.MemberRepository;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.NotSupportedException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    JPAQueryFactory factory;

    @Override
    public Member createMember(Member member) {
        return repository.save(member);
    }

    @Override
    public Member findMember(Long memberSn) {
        return repository.findById(memberSn).orElse(null);
    }

    @Override
    public Integer getMembersCount() {
        QMember qMember = QMember.member;

        return factory.selectFrom(qMember).fetch().size();
    }

    @Override
    public List<Member> findMembers(int offset, int limit, OrderBy orderBy) {
        QMember qMember = QMember.member;

        return factory.selectFrom(qMember)
                .orderBy(orderBy == OrderBy.Desc ?
                        qMember.memberSn.desc() : qMember.memberSn.asc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Member findMemberByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    @Override
    public Member updateMember(Member member) {
        return repository.save(member);
    }

    @Override
    public long lockMember(Long memberSn, YN lockYn) {
        QMember qMember = QMember.member;

        return factory.update(qMember)
                .set(qMember.lockYN, lockYn)
                .where(qMember.memberSn.eq(memberSn))
                .execute();
    }

    @Override
    public Member resetPassword(Long memberSn, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Member closeMember(Long memberSn) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLockMember(Long memberSn) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long increasePasswordVerifyFailureCnt(Long memberSn, Integer accountLockLoginFailCount) {
        QMember qMember = QMember.member;
        factory.update(qMember)
                .set(qMember.passwordFailureCount, qMember.passwordFailureCount.add(1))
                .where(qMember.memberSn.eq(memberSn))
                .execute();

        Integer lockCount = factory.select(qMember.passwordFailureCount)
                .from(qMember)
                .where(qMember.memberSn.eq(memberSn))
                .fetchOne();

        if (lockCount >= accountLockLoginFailCount) {
            lockMember(memberSn, YN.Y);
        }

        return lockCount;
    }

    @Override
    public boolean updatePassword(Long memberSn, String memberPassword) {
        QMember qMember = QMember.member;

        return factory.update(qMember)
                .set(qMember.password, memberPassword)
                .set(qMember.passwordFailureCount, 0)
                .set(qMember.lockYN, YN.N)
                .where(qMember.memberSn.eq(memberSn))
                .execute() > 0;
    }
}
