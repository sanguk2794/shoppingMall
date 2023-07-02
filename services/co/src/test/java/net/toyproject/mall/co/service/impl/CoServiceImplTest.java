package net.toyproject.mall.co.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.toyproject.mall.co.model.Co;
import net.toyproject.mall.co.model.QCo;
import net.toyproject.mall.co.repository.CoRepository;
import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.common.model.embedded.PhoneNumber;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoServiceImplTest {

    @Mock
    public CoRepository repository;

    @Mock
    public EntityManager entityManager;

    @Mock
    public JPAQueryFactory factory;

    @Mock
    private JPAQuery<Co> jpaQuery;

    @InjectMocks
    public CoServiceImpl coService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(entityManager);
    }

    @Test
    @DisplayName("企業登録")
    void createCo() {
        // given
        final Co co = getFakeCo();

        // mocking
        when(repository.save(any())).thenReturn(co);

        // when
        final Co savedCo = coService.createCo(co);

        // then
        assertEquals(co, savedCo);
    }

    @Test
    @DisplayName("企業照会")
    void findCo() {
        // given
        final Co co = getFakeCo();
        final Long fakeCoSn = 1L;
        co.setCoSn(fakeCoSn);

        // mocking
        when(repository.findById(fakeCoSn)).thenReturn(Optional.of(co));

        // when
        final Co savedCo = coService.findCo(fakeCoSn);

        // then
        assertEquals(co, savedCo);
    }

    @Test
    @DisplayName("企業カウント照会")
    void getCosCount() {
        // given
        QCo qCo = QCo.co;
        List<Co> fakeCos = new ArrayList<>();
        fakeCos.add(getFakeCo());

        // mocking
        when(factory.selectFrom(qCo)).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(fakeCos);

        // when
        final Integer count = coService.getCosCount();

        // then
        assertEquals(count, 1);
    }

    @Test
    @DisplayName("企業リスト照会")
    void findCos() {
        // given
        QCo qCo = QCo.co;
        int offset = 10;
        int limit = 10;
        OrderBy orderBy = OrderBy.Asc;
        List<Co> fakeCos = new ArrayList<>();
        fakeCos.add(getFakeCo());

        // mocking
        when(factory.selectFrom(qCo)).thenReturn(jpaQuery);
        when(jpaQuery.orderBy(qCo.coSn.asc())).thenReturn(jpaQuery);
        when(jpaQuery.offset(offset)).thenReturn(jpaQuery);
        when(jpaQuery.limit(limit)).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(fakeCos);

        // when
        final List<Co> cos = coService.findCos(offset, limit, orderBy);

        // then
        assertEquals(cos.size(), 1);
    }

    @Test
    @DisplayName("企業更新")
    void updateCo() {
        // given
        final Co co = getFakeCo();

        // mocking
        when(repository.save(any())).thenReturn(co);

        // when
        final Co savedCo = coService.updateCo(co);

        // then
        assertEquals(co, savedCo);
    }

    @Test
    @DisplayName("企業削除")
    void deleteCo() {
        // given
        final Co co = getFakeCo();
        final Long fakeCoSn = 1L;
        co.setCoSn(fakeCoSn);

        // mocking
        when(repository.save(any())).thenReturn(co);
        when(repository.findById(fakeCoSn)).thenReturn(Optional.empty());

        // when
        coService.createCo(co);
        coService.deleteCo(fakeCoSn);
        Co savedCo = coService.findCo(fakeCoSn);

        // then
        assertNull(savedCo);
    }

    private Co getFakeCo() {
        final Co co = new Co();
        co.setCoName("TEST CO");
        co.setEmailAddress("sanguk2794@gmail.com");

        final PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNo1("010");
        phoneNumber.setPhoneNo1("1111");
        phoneNumber.setPhoneNo1("2222");
        co.setFaxNumber(phoneNumber);

        return co;
    }
}
