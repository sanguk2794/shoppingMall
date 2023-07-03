package net.toyproject.mall.api.co.dto;

import lombok.Data;
import net.toyproject.mall.common.model.embedded.PhoneNumber;

@Data
public class RegisterCoDTO {

    private String coName;
    private String emailAddress;
    private PhoneNumber faxNumber;

}
