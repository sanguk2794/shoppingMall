package net.toyproject.mall.api.co.utils;

import net.toyproject.mall.api.co.dto.RegisterCoDTO;
import net.toyproject.mall.api.co.dto.UpdateCoDTO;
import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.common.model.embedded.PhoneNumber;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CoValidateUtils {

    private CoValidateUtils() {
        throw new AssertionError("this class is utility class");
    }

    public static void coValidate(String emailAddress, String coName, PhoneNumber faxNumber) {
        if (!StringUtils.isNotEmpty(emailAddress)) {
            throw new BadRequestException("emailAddress must not null");
        }

        if (!StringUtils.isNotEmpty(coName)) {
            throw new BadRequestException("coName must not null");
        }

        if (Objects.isNull(faxNumber) || !faxNumber.isValid()) {
            throw new BadRequestException("fax number is not invalid");
        }
    }

    public static void registerCoValidate(RegisterCoDTO registerCoDto) {
        coValidate(registerCoDto.getEmailAddress(), registerCoDto.getCoName(), registerCoDto.getFaxNumber());
    }

    public static void updateCoValidate(UpdateCoDTO updateCoDTO) {
        if (Objects.isNull(updateCoDTO.getCoSn())) {
            throw new BadRequestException("emailAddress must not null");
        }

        coValidate(updateCoDTO.getEmailAddress(), updateCoDTO.getCoName(), updateCoDTO.getFaxNumber());
    }


}
