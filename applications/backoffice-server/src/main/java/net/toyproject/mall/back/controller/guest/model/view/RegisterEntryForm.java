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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class RegisterEntryForm implements CommonEntryForm, Serializable {

    @NotEmpty(message = "住所を入力してください。")
    @Email(message = "メールアドレスを確認してください。")
    private String emailAddress;

    @Size(min = 8, max = 16, message = "パスワードを8~16文字で入力してください。")
    private String password;

    @Size(min = 8, max = 16, message = "パスワードを8~16文字で入力してください。")
    private String repeatPassword;

    @Size(min = 1, max = 16, message = "氏名を確認してください。")
    private String firstName;

    @Size(min = 1, max = 16, message = "名前を確認してください。")
    private String lastName;

    @Size(min = 3, max = 3, message = "郵便番号を確認してください。")
    private String zipCode1;

    @Size(min = 4, max = 4, message = "郵便番号を確認してください。")
    private String zipCode2;

    @NotEmpty(message = "住所を入力してください。")
    private String address1;

    @NotEmpty(message = "住所を入力してください。")
    private String address2;

    @NotEmpty(message = "住所を入力してください。")
    private String address3;

    private String address4;

    @Component
    public static class RegisterEntryFormValidator extends MemberFormValidator {

        @Override
        public boolean supports(@NotNull Class<?> clazz) {
            return RegisterEntryForm.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(@NotNull Object target, @NotNull Errors errors) {
            final RegisterEntryForm registerEntryForm = (RegisterEntryForm) target;
            checkPassword(errors, registerEntryForm.getPassword(), registerEntryForm.getRepeatPassword());
        }

    }
}
