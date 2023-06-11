/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.display;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.back.controller.guest.model.LoginUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController extends BaseController {

    @RequestMapping("/dashboard/")
    public String dashboardPage(@AuthenticationPrincipal LoginUser loginUser,
                                HttpServletRequest req,
                                Model model) {


        model.addAttribute("seo", getDefaultSeo());

        return "display/dashboard";
    }

}
