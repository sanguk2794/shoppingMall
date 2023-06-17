/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.util;

public class ConstUtils {

    private ConstUtils() {
        throw new AssertionError("this class is utility class");
    }

    public static final String RESOURCE_PATH  = ".*/style|webjars|js|img|noimg|noSessionAjax/.*";

    public static final String ERROR_PATH = ".*/error";

    public static final String RESOURCE_PATH_JS = "/js/**";

    public static final String RESOURCE_PATH_CSS = "/css/**";

    public static final String RESOURCE_PATH_IMG = "/img/**";

    public static final String RESOURCE_PATH_SCSS = "/scss/**";

    public static final String RESOURCE_PATH_VENDOR = "/vendor/**";

    public static final String LOGIN_URL = "/login/";

    public static final String LOGIN_PROCESSING_URL = "/login-processing/";

    public static final String LOGOUT_URL = "/logout/";

    public static final String USER_DUMMY_ID = "DEFAULT-ID";

    public static final String USER_DUMMY_PW = "DEFAULT-PW";

    public static final String LOGIN_COOKIE_NAME = "JSESSIONID";

    public static final String DEFAULT_REDIRECT_URL = "/dashboard";

    public static final String CSRF_PARAMETER_NAME = "_csrf";

    public static final String CONFIRM_DOUBLE_SUBMIT_CHECK_PARAMETER = "_double_confirm";

    public static final String COMPLETE_DOUBLE_SUBMIT_CHECK_PARAMETER = "_double_complete";
}