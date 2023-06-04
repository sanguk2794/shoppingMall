/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.restapi.client.member.model;

import lombok.Data;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.common.model.embedded.Address;
import net.toyproject.mall.common.model.embedded.Name;

import java.io.Serializable;

@Data
public class RegisterMemberDTO implements Serializable {

    private String emailAddress;
    private String password;
    private String nickName;
    private Name name;
    private Address address;
    private MemberPlatform memberPlatform;

}