/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.common;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    protected static HttpSession getSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null){
            session = req.getSession(true);
        }
        return session;
    }
}
