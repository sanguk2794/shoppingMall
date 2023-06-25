package net.toyproject.mall.co.service;

import net.toyproject.mall.co.model.Co;
import net.toyproject.mall.common.code.OrderBy;

import java.util.List;

public interface CoService {

    Co createCo(Co co);

    Co findCo(Long coSn);

    Integer getCosCount();

    List<Co> findCos(int offset, int limit, OrderBy orderBy);

    Co updateCo(Co co);

    long deleteCo(Long coSn);

}
