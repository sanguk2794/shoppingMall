package net.toyproject.mall.member.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.common.model.embedded.Address;
import net.toyproject.mall.common.model.embedded.Name;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.model.QMember;
import net.toyproject.mall.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    public MemberRepository repository;

    @Mock
    public EntityManager entityManager;

    @Mock
    public JPAQueryFactory factory;

    @Mock
    private JPAQuery<Member> jpaQuery;

    @Mock
    private JPAQuery<Integer> jpaIntegerQuery;

    @Mock
    private JPAQuery<YN> jpaYnQuery;

    @Mock
    private JPAUpdateClause jpaUpdateClause;

    @InjectMocks
    public MemberServiceImpl memberService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(entityManager);
    }

    @Test
    @DisplayName("会員登録")
    public void createMember() throws Exception {
        // given
        final Member member = getMember();

        // mocking
        when(repository.save(any()))
                .thenReturn(member);

        // when
        final Member savedMember = memberService.createMember(member);

        // then
        assertEquals(member, savedMember);
    }

    private Member getMember() {
        final Member member = new Member();
        member.setEmailAddress("sanguk2794@gmail.com");
        member.setPassword("password");
        final Name name = new Name();
        name.setFirstName("LEE");
        name.setLastName("SANG");
        member.setName(name);
        member.setPassword("1q2w3e4r");
        final Address address = new Address();
        address.setZipCode("3330853");
        address.setAddress1("123123");
        address.setAddress2("123123");
        address.setAddress3("123123");
        address.setAddress4("123123");
        member.setAddress(address);

        return member;
    }

    @Test
    @DisplayName("会員照会")
    void findMember() {
        // given
        final Member member = getMember();
        final Long fakeMemberSn = 1L;
        member.setMemberSn(fakeMemberSn);

        // mocking
        when(repository.findById(fakeMemberSn))
                .thenReturn(Optional.of(member));

        // when
        final Member savedMember = memberService.findMember(fakeMemberSn);

        // then
        assertEquals(member, savedMember);
    }

    @Test
    @DisplayName("会員カウント照会")
    void getMembersCount() {
        // given
        QMember qMember = QMember.member;

        // mocking
        when(factory.selectFrom(qMember)).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(new ArrayList<>());

        // when
        final Integer count = memberService.getMembersCount();

        // then
        assertEquals(count, 0);
    }

    @Test
    @DisplayName("会員リスト照会（Desc）")
    void findMembersDesc() {
        // given
        QMember qMember = QMember.member;
        int offset = 10;
        int limit = 10;
        OrderBy orderBy = OrderBy.Desc;

        // mocking
        when(factory.selectFrom(qMember)).thenReturn(jpaQuery);
        when(jpaQuery.orderBy(qMember.memberSn.desc())).thenReturn(jpaQuery);
        when(jpaQuery.offset(offset)).thenReturn(jpaQuery);
        when(jpaQuery.limit(limit)).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(new ArrayList<>());

        // when
        final List<Member> members = memberService.findMembers(offset, limit, orderBy);

        // then
        assertEquals(members.size(), 0);
    }

    @Test
    @DisplayName("会員リスト照会（ASC）")
    void findMembersAsc() {
        // given
        QMember qMember = QMember.member;
        int offset = 10;
        int limit = 10;
        OrderBy orderBy = OrderBy.Asc;

        // mocking
        when(factory.selectFrom(qMember)).thenReturn(jpaQuery);
        when(jpaQuery.orderBy(qMember.memberSn.asc())).thenReturn(jpaQuery);
        when(jpaQuery.offset(offset)).thenReturn(jpaQuery);
        when(jpaQuery.limit(limit)).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(new ArrayList<>());

        // when
        final List<Member> members = memberService.findMembers(offset, limit, orderBy);

        // then
        assertEquals(members.size(), 0);
    }

    @Test
    @DisplayName("会員照会")
    void findMemberByEmailAddress() {
        // given
        final Member member = getMember();
        final String emailAddress = "sanguk2794@gmail.com";
        member.setEmailAddress(emailAddress);

        // mocking
        when(repository.findByEmailAddress(emailAddress)).thenReturn(member);

        // when
        final Member savedMember = repository.findByEmailAddress(emailAddress);

        // then
        assertEquals(member, savedMember);
    }

    @Test
    @DisplayName("会員情報変更")
    void updateMember() {
        // given
        final Member member = getMember();

        // mocking
        when(repository.save(any()))
                .thenReturn(member);

        // when
        final Member savedMember = memberService.updateMember(member);

        // then
        assertEquals(member, savedMember);
    }

    @Test
    @DisplayName("会員ロック（lockYn = Y）")
    void lockMemberY() {
        // given
        Long fakeMemberSn = 1L;
        QMember qMember = QMember.member;
        YN lockYn = YN.Y;

        // mocking
        when(factory.update(qMember)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.set(qMember.lockYN, lockYn)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.execute()).thenReturn(1L);

        // when
        final Long count = memberService.lockMember(fakeMemberSn, lockYn);

        // then
        assertEquals(count, 1);
    }

    @Test
    @DisplayName("会員ロック（lockYn = N）")
    void lockMemberN() {
        // given
        Long fakeMemberSn = 1L;
        QMember qMember = QMember.member;
        YN lockYn = YN.N;

        // mocking
        when(factory.update(qMember)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.set(qMember.lockYN, lockYn)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.execute()).thenReturn(1L);

        // when
        final Long count = memberService.lockMember(fakeMemberSn, lockYn);

        // then
        assertEquals(count, 1);
    }

    @Test
    @DisplayName("パスワード変更（未実装）")
    void resetPassword() {
        // given
        Long fakeMemberSn = 1L;
        String newPassword = "1q2w3e4r";

        // when then
        assertThrows(UnsupportedOperationException.class, () -> memberService.resetPassword(fakeMemberSn, newPassword));
    }

    @Test
    @DisplayName("会員退会（未実装）")
    void closeMember() {
        // given
        Long fakeMemberSn = 1L;

        // when then
        assertThrows(UnsupportedOperationException.class, () -> memberService.closeMember(fakeMemberSn));
    }

    @Test
    @DisplayName("ロック可否（成功）")
    void isLockMemberSuccess() {
        // given
        QMember qMember = QMember.member;
        Long fakeMemberSn = 1L;

        // mocking
        when(factory.select(qMember.lockYN)).thenReturn(jpaYnQuery);
        when(jpaYnQuery.from(qMember)).thenReturn(jpaYnQuery);
        when(jpaYnQuery.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaYnQuery);
        when(jpaYnQuery.fetchOne()).thenReturn(YN.Y);

        // when
        final boolean yn = memberService.isLockMember(fakeMemberSn);

        // then
        assertTrue(yn);
    }

    @Test
    @DisplayName("ロック可否（失敗）")
    void isLockMemberFailure() {
        // given
        QMember qMember = QMember.member;
        Long fakeMemberSn = 1L;

        // mocking
        when(factory.select(qMember.lockYN)).thenReturn(jpaYnQuery);
        when(jpaYnQuery.from(qMember)).thenReturn(jpaYnQuery);
        when(jpaYnQuery.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaYnQuery);
        when(jpaYnQuery.fetchOne()).thenReturn(YN.N);

        // when
        final boolean yn = memberService.isLockMember(fakeMemberSn);

        // then
        assertFalse(yn);
    }


    @Test
    void increasePasswordVerifyFailureCnt() {
        // given
        Long fakeMemberSn = 1L;
        QMember qMember = QMember.member;
        YN lockYn = YN.Y;

        // mocking
        when(factory.update(qMember)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.set(qMember.passwordFailureCount, qMember.passwordFailureCount.add(1))).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.execute()).thenReturn(1L);

        when(factory.select(qMember.passwordFailureCount)).thenReturn(jpaIntegerQuery);
        when(jpaIntegerQuery.from(qMember)).thenReturn(jpaIntegerQuery);
        when(jpaIntegerQuery.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaIntegerQuery);
        when(jpaIntegerQuery.fetchOne()).thenReturn(1);

        // when
        final Long count = memberService.increasePasswordVerifyFailureCnt(fakeMemberSn, 4);

        // then
        assertEquals(count, 1);
    }

    @Test
    void updatePassword() {
        // given
        Long fakeMemberSn = 1L;
        QMember qMember = QMember.member;
        String password = "1q2w3e4r";

        // mocking
        when(factory.update(qMember)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.set(qMember.password, password)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.set(qMember.passwordFailureCount, 0)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.set(qMember.lockYN, YN.N)).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.where(qMember.memberSn.eq(fakeMemberSn))).thenReturn(jpaUpdateClause);
        when(jpaUpdateClause.execute()).thenReturn(1L);

        // when
        final boolean result = memberService.updatePassword(fakeMemberSn, password);

        // then
        assertTrue(result);
    }
}
