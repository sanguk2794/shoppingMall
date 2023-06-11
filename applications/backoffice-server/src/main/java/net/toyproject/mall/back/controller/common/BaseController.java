/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.common;

import net.toyproject.mall.back.controller.common.model.Seo;
import net.toyproject.mall.back.controller.common.model.ValidResponse;
import net.toyproject.mall.back.controller.guest.model.view.RegisterEntryForm;
import net.toyproject.mall.back.controller.guest.model.view.common.CommonEntryForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

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
}
