/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member.dto;

import lombok.Data;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.member.model.embedded.Address;
import net.toyproject.mall.member.model.embedded.Name;

@Data
public class UpdateMemberDTO {

    private Long memberSn;
    private String password;
    private String nickName;
    private Name name;
    private Address address;

}
