/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member;

import io.swagger.annotations.ApiParam;
import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.api.member.dto.RegisterMemberDTO;
import net.toyproject.mall.api.member.dto.UpdateMemberDTO;
import net.toyproject.mall.api.util.MemberUtils;
import net.toyproject.mall.api.util.MemberValidate;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController(value = "member")
public class MemberApi {

    @Autowired
    MemberService memberService;

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<Member> registerMember(
            @RequestBody @ApiParam(required = true) @Validated RegisterMemberDTO registerMemberDTO) {

        MemberValidate.registerMemberValidate(registerMemberDTO);
        return new ResponseEntity<>(
                memberService.createMember(MemberUtils.registerToMember(registerMemberDTO)), HttpStatus.OK);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<Member> findMember(@RequestParam @Validated Long memberSn) {

        Member member = memberService.findMember(memberSn);
        if (Objects.isNull(member)) {
            throw new BadRequestException("member was not found");
        }

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @RequestMapping(value="/", method = RequestMethod.PUT)
    public ResponseEntity<Member> updateMember(
            @RequestBody @ApiParam(required = true) @Validated UpdateMemberDTO updateMemberDTO) {

        MemberValidate.updateMemberValidate(updateMemberDTO);
        Member member = memberService.findMember(updateMemberDTO.getMemberSn());
        if (Objects.isNull(member)) {
            throw new IllegalArgumentException("member was not found");
        }


        return new ResponseEntity<>(
                memberService.updateMember(MemberUtils.updateToMember(updateMemberDTO, member)), HttpStatus.OK);
    }

    @RequestMapping(value="/", method = RequestMethod.DELETE)
    public ResponseEntity<Member> deleteUser(
            @RequestBody @ApiParam(required = true) @Validated Long memberSn) {

        if (Objects.isNull(memberService.findMember(memberSn))) {
            throw new IllegalArgumentException("member was not found");
        }

        memberService.closeMember(memberSn);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
