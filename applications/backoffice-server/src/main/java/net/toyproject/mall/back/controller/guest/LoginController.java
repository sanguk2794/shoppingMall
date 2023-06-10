/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.controller.guest;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.back.controller.common.model.Seo;
import net.toyproject.mall.back.controller.guest.model.LoginUser;
import net.toyproject.mall.back.util.ConstUtils;
import net.toyproject.mall.back.util.SessionUtils;
import net.toyproject.mall.restapi.client.member.MemberApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController extends BaseController {

    private static final String LOGIN_FORM_SUBMIT_URL = "/register/entry/validate/";

    @Autowired
    MemberApi memberApi;

    @GetMapping(ConstUtils.LOGIN_URL)
    public String loginPage(@RequestParam(name = "error", required = false) String error,
                            @AuthenticationPrincipal LoginUser loginUser,
                            HttpServletRequest req, Model model) {

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DEFAULT_REDIRECT_URL;
        }

        final HttpSession session = SessionUtils.getSession(req);
        if (!Objects.isNull(error)) {
            final String errorMsg = getErrorMsg(error);
            if (errorMsg != null) {
                model.addAttribute("errorMessage", errorMsg);
            }
        }

        model.addAttribute("seo", getDefaultSeo());
        model.addAttribute("loginFormSubmitUrl", ConstUtils.LOGIN_PROCESSING_URL);

        return "guest/login";
    }

    private static String getErrorMsg(String error) {
        String errorMsg = ConstUtils.INPUT_ERROR_MESSAGE;
        switch (error) {
            case ConstUtils.INPUT_ERROR:
            case ConstUtils.TOKEN_WRONG:
                errorMsg = ConstUtils.TOKEN_WRONG_MESSAGE;
                break;

            case ConstUtils.TOKEN_LOOK:
                errorMsg = ConstUtils.TOKEN_LOOK_MESSAGE;
                break;
        }

        return errorMsg;
    }
}
