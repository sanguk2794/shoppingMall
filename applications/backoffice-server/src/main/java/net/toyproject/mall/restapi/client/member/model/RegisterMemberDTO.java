/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.restapi.client.member.model;

import lombok.Data;
import net.toyproject.mall.back.controller.guest.model.view.RegisterEntryForm;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.common.model.embedded.Address;
import net.toyproject.mall.common.model.embedded.Name;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

@Data
public class RegisterMemberDTO implements Serializable {

    private String emailAddress;
    private String password;
    private String nickName;
    private Name name;
    private Address address;
    private MemberPlatform memberPlatform;

    public static RegisterMemberDTO toRegisterMemberDTO(RegisterEntryForm form,
                                                        BCryptPasswordEncoder bCryptPasswordEncoder) {

        final RegisterMemberDTO member = new RegisterMemberDTO();
        member.setEmailAddress(form.getEmailAddress());
        member.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));

        final Name name = new Name();
        name.setFirstName(form.getFirstName());
        name.setLastName(form.getLastName());
        member.setName(name);

        final Address address = new Address();
        address.setZipCode(form.getZipCode1() + "-" + form.getZipCode2());
        address.setAddress1(form.getAddress1());
        address.setAddress2(form.getAddress2());
        address.setAddress3(form.getAddress3());
        address.setAddress4(form.getAddress4());
        member.setAddress(address);

        member.setMemberPlatform(MemberPlatform.BackOffice);

        return member;
    }
}