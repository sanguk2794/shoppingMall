/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.restapi.client.member.model;

import lombok.Data;

@Data
public class ResetPasswordDTO {

    private Long memberSn;

    private String password;

    public static ResetPasswordDTO toResetPasswordDTO(Long memberSn,
                                                      String encryptedPassword) {

        final ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setMemberSn(memberSn);
        resetPasswordDTO.setPassword(encryptedPassword);

        return resetPasswordDTO;
    }
}
