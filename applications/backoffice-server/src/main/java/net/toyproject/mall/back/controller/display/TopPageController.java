/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.display;

import net.toyproject.mall.restapi.client.member.MemberApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TopPageController {

    @RequestMapping("/")
    public String registerProcess(HttpServletRequest req,
                                  RedirectAttributes redirectAttributes) {

        return "display/topPage";

    }

}
