/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.controller.guest;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.back.controller.common.model.Seo;
import net.toyproject.mall.restapi.client.member.MemberApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    @Autowired
    MemberApi memberApi;

    @GetMapping("/guest/login/")
    public String loginPage(Model model, HttpServletRequest req) {

        Seo seo = new Seo();
        seo.setTitle("Login");
        model.addAttribute("seo", seo);

        return "guest/login";
    }
}
