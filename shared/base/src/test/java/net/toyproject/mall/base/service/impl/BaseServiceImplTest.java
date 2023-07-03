package net.toyproject.mall.base.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
class BaseServiceImplTest {

    @Mock
    public EntityManager entityManager;

    @Mock
    public JPAQueryFactory factory;

    @Mock
    private JPAUpdateClause jpaUpdateClause;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(entityManager);
    }

}
