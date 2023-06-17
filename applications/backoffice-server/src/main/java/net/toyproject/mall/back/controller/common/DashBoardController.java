/**
 * @author sanguk on 2023/06/18
 */

package net.toyproject.mall.back.controller.common;

import net.toyproject.mall.back.controller.common.model.DashBoard;
import net.toyproject.mall.back.controller.common.model.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController extends BaseController {

    protected DashBoard getDashboard(String name, String detail) {
        final DashBoard dashBoard = new DashBoard();
        dashBoard.setName(name);
        dashBoard.setDetail(detail);

        return dashBoard;
    }

    protected int getOffset(Integer pageNumber, int limitCount) {
        int offset = 0;
        if (!Objects.isNull(pageNumber)) {
            offset = limitCount * (pageNumber - 1);
        }

        return offset;
    }

    protected Paging setPagingParameter(int totalCount,
                                        int limitCount,
                                        Integer currentPageNumber) {

        Paging paging = new Paging();
        paging.setTotalCount(totalCount);
        paging.setLimitCount(limitCount);

        // 最後のページ番号を取得
        int endPageNum = totalCount % limitCount == 0 ?
                totalCount / limitCount : totalCount / limitCount + 1;
        paging.setEndPage(endPageNum);

        if (currentPageNumber != null) {
            paging.setCurrentPage(currentPageNumber);
        }

        return paging;
    }

}
