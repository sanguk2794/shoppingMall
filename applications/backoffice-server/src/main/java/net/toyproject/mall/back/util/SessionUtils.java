/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    private SessionUtils() {
        throw new AssertionError("this class is utility class");
    }

    public static HttpSession getSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null){
            session = req.getSession(true);
        }

        return session;
    }
}
