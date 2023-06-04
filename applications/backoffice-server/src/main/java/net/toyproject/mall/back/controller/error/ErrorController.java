/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.error;

import net.toyproject.mall.back.controller.common.model.Seo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController extends AbstractErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(ERROR_PATH)
    public String handleErrors(HttpServletRequest request, Model model) {

        Seo seo = new Seo();
        seo.setTitle("Error");
        model.addAttribute("seo", seo);

        return "error/error";
    }
}
