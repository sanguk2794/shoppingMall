/**
 * @author sanguk on 2023/06/08
 */

package net.toyproject.mall.back.controller.guest.model.view;

import lombok.Data;
import net.toyproject.mall.back.controller.guest.model.view.Validator.MemberFormValidator;
import net.toyproject.mall.back.controller.guest.model.view.common.CommonEntryForm;
import net.toyproject.mall.common.model.embedded.Name;
import net.toyproject.mall.restapi.client.member.model.Member;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class RemindEntryForm implements CommonEntryForm, Serializable {

    @NotEmpty(message = "住所を入力してください。")
    @Email(message = "メールアドレスを確認してください。")
    private String emailAddress;

    @Size(min = 1, max = 16, message = "氏名を確認してください。")
    private String firstName;

    @Size(min = 1, max = 16, message = "名前を確認してください。")
    private String lastName;

    private Long memberSn;

    @Component
    public static class RemindEntryFormValidator extends MemberFormValidator {

        @Override
        public boolean supports(@NotNull Class<?> clazz) {
            return RemindEntryForm.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(@NotNull Object target, @NotNull Errors errors) {
            final RemindEntryForm remindEntryForm = (RemindEntryForm) target;
            checkExistMember(errors, remindEntryForm);
        }

        public void checkExistMember(Errors errors, RemindEntryForm remindEntryForm) {
            Member member = null;
            try {
                member = memberApi.getMemberByEmailAddress(remindEntryForm.getEmailAddress());

            } catch (Exception ignored) {
                // member does not exist
            }

            if (member == null) {
                errors.rejectValue("emailAddress", "", "入力した情報が間違っています。");

            } else {
                final Name name = member.getName();
                if (validName(remindEntryForm, name)) {
                    errors.rejectValue("emailAddress", "", "入力した情報が間違っています。");
                }
            }
        }
    }

    private static boolean validName(RemindEntryForm remindEntryForm, Name name) {
        return !StringUtils.equals(name.getFirstName(), remindEntryForm.getFirstName())
                || !StringUtils.equals(name.getLastName(), remindEntryForm.getLastName());
    }
}
