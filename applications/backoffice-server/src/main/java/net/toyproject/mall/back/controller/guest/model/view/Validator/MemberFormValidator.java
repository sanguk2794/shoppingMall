/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.back.controller.guest.model.view.Validator;

import net.toyproject.mall.restapi.client.member.MemberApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public abstract class MemberFormValidator implements Validator {

    @Autowired
    protected MemberApi memberApi;


    protected void checkPassword(Errors errors, String password, String password2) {
        if (!StringUtils.equals(password, password2)) {
            errors.rejectValue("repeatPassword", "", "入力したパスワードが間違っています。");
        }
    }
}
