/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.guest;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.back.controller.guest.model.LoginUser;
import net.toyproject.mall.back.controller.guest.model.view.RemindEntryForm;
import net.toyproject.mall.back.controller.guest.model.view.ResetPasswordEntryForm;
import net.toyproject.mall.back.util.ConstUtils;
import net.toyproject.mall.back.util.SessionUtils;
import net.toyproject.mall.restapi.client.member.MemberApi;
import net.toyproject.mall.restapi.client.member.model.Member;
import net.toyproject.mall.restapi.client.member.model.ResetPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class RemindController extends BaseController {

    private static final String REMIND_PAGE_URL = "/remind/";
    private static final String REMIND_ENTRY_PAGE_URL = "/remind/entry/";
    private static final String REMIND_ENTRY_FORM_VALIDATE_PAGE_URL = "/remind/entry/validate/";
    private final String REMIND_FORM_NAME = "remindEntryForm";
    private static final String RESET_PASSWORD_PAGE_URL = "/reset-password/";
    private static final String RESET_PASSWORD_ENTRY_PAGE_URL = "/reset-password/entry/";
    private static final String RESET_PASSWORD_FORM_VALIDATE_PAGE_URL = "/reset-password/entry/validate/";
    private static final String REMIND_COMPLETE_PAGE_URL = "/remind/complete/";
    private final String RESET_PASSWORD_FORM_NAME = "resetPasswordEntryForm";

    @Autowired
    private MemberApi memberApi;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RemindEntryForm.RemindEntryFormValidator remindEntryFormValidator;

    @Autowired
    private ResetPasswordEntryForm.ResetPasswordEntryFormValidator resetPasswordEntryFormValidator;

    @GetMapping(REMIND_PAGE_URL)
    public String remindPage(@AuthenticationPrincipal LoginUser loginUser,
                             HttpServletRequest req) {

        final HttpSession session = SessionUtils.getSession(req);
        session.removeAttribute(REMIND_FORM_NAME);

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DEFAULT_REDIRECT_URL;
        }

        final RemindEntryForm form = new RemindEntryForm();
        session.setAttribute(REMIND_FORM_NAME, form);

        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REMIND_ENTRY_PAGE_URL;
    }

    @GetMapping(REMIND_ENTRY_PAGE_URL)
    public String remindEntryPage(@AuthenticationPrincipal LoginUser loginUser,
                                  HttpServletRequest req,
                                  Model model) {

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DEFAULT_REDIRECT_URL;
        }

        final HttpSession session = SessionUtils.getSession(req);
        final RemindEntryForm form = (RemindEntryForm) session.getAttribute(REMIND_FORM_NAME);
        if (form == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REMIND_PAGE_URL;
        }

        session.setAttribute(REMIND_FORM_NAME, form);
        model.addAttribute("formData", form);
        model.addAttribute("seo", getDefaultSeo());
        model.addAttribute("remindEntryFormSubmitUrl", REMIND_ENTRY_FORM_VALIDATE_PAGE_URL);

        return "guest/remind/remindEntry";
    }

    @PostMapping(REMIND_ENTRY_FORM_VALIDATE_PAGE_URL)
    @ResponseBody
    public ResponseEntity<?> remindEntryValidate(@Valid RemindEntryForm form,
                                                 HttpServletRequest req,
                                                 BindingResult errors) {

        validate(form, errors, remindEntryFormValidator);
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(bindErrors(errors));
        }

        final Member member = memberApi.getMemberByEmailAddress(form.getEmailAddress());
        form.setMemberSn(member.getMemberSn());

        final HttpSession session = SessionUtils.getSession(req);
        session.setAttribute(REMIND_FORM_NAME, form);

        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("nextUrl", RESET_PASSWORD_PAGE_URL);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(RESET_PASSWORD_PAGE_URL)
    public String restPasswordPage(@AuthenticationPrincipal LoginUser loginUser,
                             HttpServletRequest req) {

        final HttpSession session = SessionUtils.getSession(req);
        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DEFAULT_REDIRECT_URL;
        }

        final RemindEntryForm remindEntryForm = (RemindEntryForm) session.getAttribute(REMIND_FORM_NAME);
        if (remindEntryForm == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REMIND_PAGE_URL;
        }

        final ResetPasswordEntryForm resetPasswordEntryForm = new ResetPasswordEntryForm();
        session.setAttribute(RESET_PASSWORD_FORM_NAME, resetPasswordEntryForm);

        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + RESET_PASSWORD_ENTRY_PAGE_URL;
    }

    @GetMapping(RESET_PASSWORD_ENTRY_PAGE_URL)
    public String resetPasswordEntryPage(@AuthenticationPrincipal LoginUser loginUser,
                                         HttpServletRequest req,
                                         Model model) {

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DEFAULT_REDIRECT_URL;
        }

        final HttpSession session = SessionUtils.getSession(req);
        final RemindEntryForm remindEntryForm = (RemindEntryForm) session.getAttribute(REMIND_FORM_NAME);
        if (remindEntryForm == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REMIND_PAGE_URL;
        }

        final ResetPasswordEntryForm resetPasswordEntryForm = (ResetPasswordEntryForm) session.getAttribute(RESET_PASSWORD_FORM_NAME);
        if (resetPasswordEntryForm == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + RESET_PASSWORD_PAGE_URL;
        }

        session.setAttribute(REMIND_FORM_NAME, remindEntryForm);
        session.setAttribute(RESET_PASSWORD_FORM_NAME, resetPasswordEntryForm);

        model.addAttribute("formData", resetPasswordEntryForm);
        model.addAttribute("seo", getDefaultSeo());
        model.addAttribute("resetPasswordEntryFormSubmitUrl", RESET_PASSWORD_FORM_VALIDATE_PAGE_URL);

        return "guest/remind/resetPasswordEntry";
    }

    @PostMapping(RESET_PASSWORD_FORM_VALIDATE_PAGE_URL)
    @ResponseBody
    public ResponseEntity<?> restPasswordFormValidate(@Valid ResetPasswordEntryForm form,
                                                      HttpServletRequest req,
                                                      BindingResult errors) {

        validate(form, errors, resetPasswordEntryFormValidator);
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(bindErrors(errors));
        }

        final HttpSession session = SessionUtils.getSession(req);
        session.setAttribute(RESET_PASSWORD_FORM_NAME, form);

        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("nextUrl", REMIND_COMPLETE_PAGE_URL);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(REMIND_COMPLETE_PAGE_URL)
    public String remindCompletePage(HttpServletRequest req,
                                       Model model) {

        final HttpSession session = SessionUtils.getSession(req);
        final RemindEntryForm remindEntryForm = (RemindEntryForm) session.getAttribute(REMIND_FORM_NAME);
        if (remindEntryForm == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REMIND_PAGE_URL;
        }

        final ResetPasswordEntryForm resetPasswordEntryForm = (ResetPasswordEntryForm) session.getAttribute(RESET_PASSWORD_FORM_NAME);
        if (resetPasswordEntryForm == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + RESET_PASSWORD_FORM_NAME;
        }

        memberApi.resetPassword(
                ResetPasswordDTO.toResetPasswordDTO(
                        remindEntryForm.getMemberSn(),
                        bCryptPasswordEncoder.encode(resetPasswordEntryForm.getPassword())));

        model.addAttribute("seo", getDefaultSeo());

        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.LOGIN_URL;
    }
}
