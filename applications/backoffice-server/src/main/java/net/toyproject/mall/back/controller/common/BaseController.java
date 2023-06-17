/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.common;

import net.toyproject.mall.back.controller.common.model.Paging;
import net.toyproject.mall.back.controller.common.model.Seo;
import net.toyproject.mall.back.controller.common.model.ValidResponse;
import net.toyproject.mall.back.controller.guest.model.view.common.CommonEntryForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class BaseController {

    private static final int DEFAULT_PRINT_PAGE_COUNT = 10;

    protected Seo getDefaultSeo() {
        Seo seo = new Seo();
        seo.setTitle("DEFAULT_TITLE");
        seo.setDescription("DEFAULT_DESCRIPTION");
        seo.setKeyword("DEFAULT_KEYWORD");

        return seo;
    }

    protected List<ValidResponse> bindErrors(BindingResult bindingResult) {
        List<ValidResponse> errors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(
                    new ValidResponse(false, error.getField(), error.getDefaultMessage()));
        }

        return errors;
    }

    public void validate(CommonEntryForm target, BindingResult bindingResult, Validator... validators) {
        for (Validator validator : validators) {
            validator.validate(target, bindingResult);
        }
    }

    protected static int getOffset(Integer pageNumber, int limitCount) {
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
