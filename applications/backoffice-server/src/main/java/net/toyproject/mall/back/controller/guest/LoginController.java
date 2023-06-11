/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.controller.guest;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.back.controller.guest.model.LoginUser;
import net.toyproject.mall.back.util.ConstUtils;
import net.toyproject.mall.common.code.LoginProcessStatusCode;
import net.toyproject.mall.restapi.client.member.MemberApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class LoginController extends BaseController {

    private static final String LOGIN_FORM_SUBMIT_URL = "/register/entry/validate/";

    @Autowired
    MemberApi memberApi;

    @GetMapping(ConstUtils.LOGIN_URL)
    public String loginPage(@RequestParam(name = "error", required = false) String errorCode,
                            @AuthenticationPrincipal LoginUser loginUser,
                            HttpServletRequest req, Model model) {

        if (loginUser != null) {
            return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ConstUtils.DEFAULT_REDIRECT_URL;
        }

        if (!Objects.isNull(errorCode)) {
            model.addAttribute("errorMessage", getErrorMessage(errorCode));
        }

        model.addAttribute("seo", getDefaultSeo());
        model.addAttribute("loginFormSubmitUrl", ConstUtils.LOGIN_PROCESSING_URL);

        return "guest/login/login";
    }

    private static String getErrorMessage(String errorCode) {
        return LoginProcessStatusCode.valueOf(errorCode).getMessage();
    }
}
