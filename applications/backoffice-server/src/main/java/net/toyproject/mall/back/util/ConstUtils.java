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
}
