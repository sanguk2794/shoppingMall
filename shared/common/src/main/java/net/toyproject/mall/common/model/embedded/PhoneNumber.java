/**
 * @author sanguk on 2023/06/25
 */

package net.toyproject.mall.common.model.embedded;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Data
public class PhoneNumber {

    /**
     * 2-3文字
     * */
    private static final String PHONE_NO_1_REGEX = "^\\d{2,3}$";

    /**
     * 3-4文字
     * */
    private static final String PHONE_NO_2_REGEX = "^\\d{3,4}$";

    /**
     * 4文字
     * */
    private static final String PHONE_NO_3_REGEX = "^\\d{4}$";

    private static final Pattern PHONE_NO_1_PATTERN = Pattern.compile(PHONE_NO_1_REGEX);
    private static final Pattern PHONE_NO_2_PATTERN = Pattern.compile(PHONE_NO_2_REGEX);
    private static final Pattern PHONE_NO_3_PATTERN = Pattern.compile(PHONE_NO_3_REGEX);

    private String phoneNo1;
    private String phoneNo2;
    private String phoneNo3;

    public boolean isValid() {
        return (!StringUtils.hasLength(phoneNo1) || !PHONE_NO_1_PATTERN.matcher(phoneNo1).matches())
                && (!StringUtils.hasLength(phoneNo2) || !PHONE_NO_2_PATTERN.matcher(phoneNo2).matches())
                && (!StringUtils.hasLength(phoneNo2) || !PHONE_NO_3_PATTERN.matcher(phoneNo2).matches());
    }
}
