/**
 * @author sanguk on 2023/06/12
 */

package net.toyproject.mall.back.controller.common.model;

import lombok.Data;

@Data
public class Paging {

    private int totalCount;

    private int limitCount;

    private int currentPage = 1;

    private int endPage;

}
