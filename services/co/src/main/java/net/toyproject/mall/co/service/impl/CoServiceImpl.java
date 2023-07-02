/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.co.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.toyproject.mall.co.model.Co;
import net.toyproject.mall.co.model.QCo;
import net.toyproject.mall.co.repository.CoRepository;
import net.toyproject.mall.co.service.CoService;
import net.toyproject.mall.common.code.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CoServiceImpl implements CoService {

    @Autowired
    private CoRepository repository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    JPAQueryFactory factory;

    @Override
    public Co createCo(Co co) {
        return repository.save(co);
    }

    @Override
    public Co findCo(Long coSn) {
        return repository.findById(coSn).orElse(null);
    }

    @Override
    public Integer getCosCount() {
        return factory.selectFrom(QCo.co).fetch().size();
    }

    @Override
    public List<Co> findCos(int offset, int limit, OrderBy orderBy) {
        QCo qCo = QCo.co;

        return factory.selectFrom(qCo)
                .orderBy(orderBy == OrderBy.Desc ?
                        qCo.coSn.desc() : qCo.coSn.asc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Co updateCo(Co co) {
        return repository.save(co);
    }

    @Override
    public void deleteCo(Long coSn) {
        repository.deleteById(coSn);
    }
}
