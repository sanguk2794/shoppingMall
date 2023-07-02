/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.guest;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.back.controller.guest.model.LoginUser;
import net.toyproject.mall.back.controller.guest.model.view.RegisterEntryForm;
import net.toyproject.mall.back.util.ConstUtils;
import net.toyproject.mall.back.util.SessionUtils;
import net.toyproject.mall.restapi.client.member.MemberApi;
import net.toyproject.mall.restapi.client.member.model.RegisterMemberDTO;
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
public class RegisterController extends BaseController {

    private static final String REGISTER_PAGE_URL = "/register/";
    private static final String REGISTER_ENTRY_PAGE_URL = "/register/entry/";
    private static final String REGISTER_ENTRY_FORM_VALIDATE_PAGE_URL = "/register/entry/validate/";
    private static final String REGISTER_COMPLETE_PAGE_URL = "/register/complete/";
    private final String FORM_NAME = "registerEntryForm";

    @Autowired
    private MemberApi memberApi;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RegisterEntryForm.RegisterEntryFormValidator registerEntryFormValidator;

    @GetMapping(REGISTER_PAGE_URL)
    public String registerPage(@AuthenticationPrincipal LoginUser loginUser,
                               HttpServletRequest req) {

        final HttpSession session = SessionUtils.getSession(req);
        clearRegisterSessions(session);

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DASHBOARD_URL;
        }

        final RegisterEntryForm form = new RegisterEntryForm();
        session.setAttribute(FORM_NAME, form);

        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REGISTER_ENTRY_PAGE_URL;
    }

    @GetMapping(REGISTER_ENTRY_PAGE_URL)
    public String registerEntryPage(@AuthenticationPrincipal LoginUser loginUser,
                                    HttpServletRequest req,
                                    Model model) {

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DASHBOARD_URL;
        }

        final HttpSession session = SessionUtils.getSession(req);
        final RegisterEntryForm form = (RegisterEntryForm) session.getAttribute(FORM_NAME);
        if (form == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REGISTER_PAGE_URL;
        }

        session.setAttribute(FORM_NAME, form);
        model.addAttribute("formData", form);
        model.addAttribute("seo", getDefaultSeo());
        model.addAttribute("registerEntryFormSubmitUrl", REGISTER_ENTRY_FORM_VALIDATE_PAGE_URL);

        return "guest/register/registerEntry";
    }

    @PostMapping(REGISTER_ENTRY_FORM_VALIDATE_PAGE_URL)
    @ResponseBody
    public ResponseEntity<?> registerEntryValidate(@Valid RegisterEntryForm form,
                                                   HttpServletRequest req,
                                                   BindingResult errors) {

        validate(form, errors, registerEntryFormValidator);
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(bindErrors(errors));
        }

        final HttpSession session = SessionUtils.getSession(req);
        session.setAttribute(FORM_NAME, form);

        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("nextUrl", REGISTER_COMPLETE_PAGE_URL);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(REGISTER_COMPLETE_PAGE_URL)
    public String registerCompletePage(HttpServletRequest req,
                                       Model model) {

        final HttpSession session = SessionUtils.getSession(req);
        final RegisterEntryForm form = (RegisterEntryForm) session.getAttribute(FORM_NAME);
        if (form == null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + REGISTER_PAGE_URL;
        }

        memberApi.registerMember(
                RegisterMemberDTO.toRegisterMemberDTO(form, bCryptPasswordEncoder));

        clearRegisterSessions(session);

        model.addAttribute("seo", getDefaultSeo());

        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.LOGIN_URL;
    }

    private void clearRegisterSessions(HttpSession session) {
        session.removeAttribute(FORM_NAME);
    }

}
