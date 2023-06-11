/**
 * @author sanguk on 2023/06/08
 */

package net.toyproject.mall.back.controller.guest.model.view;

import lombok.Data;
import net.toyproject.mall.back.controller.guest.model.view.Validator.MemberFormValidator;
import net.toyproject.mall.back.controller.guest.model.view.common.CommonEntryForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ResetPasswordEntryForm implements CommonEntryForm, Serializable {

    @Size(min = 8, max = 16, message = "パスワードを8~16文字で入力してください。")
    private String password;

    @Size(min = 8, max = 16, message = "パスワードを8~16文字で入力してください。")
    private String repeatPassword;

    @Component
    public static class ResetPasswordEntryFormValidator extends MemberFormValidator {

        @Override
        public boolean supports(@NotNull Class<?> clazz) {
            return ResetPasswordEntryForm.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(@NotNull Object target, @NotNull Errors errors) {
            final ResetPasswordEntryForm resetPasswordEntryForm = (ResetPasswordEntryForm) target;
            checkPassword(errors, resetPasswordEntryForm.getPassword(), resetPasswordEntryForm.getRepeatPassword());
        }
    }

}
