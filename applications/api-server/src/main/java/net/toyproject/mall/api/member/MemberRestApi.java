/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.api.member.dto.RegisterMemberDTO;
import net.toyproject.mall.api.member.dto.UpdateMemberDTO;
import net.toyproject.mall.api.util.MemberUtils;
import net.toyproject.mall.api.util.MemberValidateUtils;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v1/member")
public class MemberRestApi {

    @Autowired
    MemberService memberService;

    @Operation(summary = "Register Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Register Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.POST)
    public ResponseEntity<Member> registerMember(
            @Parameter @RequestBody @Validated RegisterMemberDTO registerMemberDTO) {

        MemberValidateUtils.registerMemberValidate(registerMemberDTO);
        return new ResponseEntity<>(
                memberService.createMember(MemberUtils.registerToMember(registerMemberDTO)), HttpStatus.OK);
    }

    @Operation(summary = "Get Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.GET)
    public ResponseEntity<Member> getMember(
            @Parameter @RequestParam @Validated Long memberSn) {

        Member member = memberService.findMember(memberSn);
        if (Objects.isNull(member)) {
            throw new BadRequestException("member was not found");
        }

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Operation(summary = "Update Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.PUT)
    public ResponseEntity<Member> updateMember(
            @Parameter @RequestBody @Validated UpdateMemberDTO updateMemberDTO) {

        MemberValidateUtils.updateMemberValidate(updateMemberDTO);
        Member member = memberService.findMember(updateMemberDTO.getMemberSn());
        if (Objects.isNull(member)) {
            throw new BadRequestException("member was not found");
        }

        return new ResponseEntity<>(
                memberService.updateMember(MemberUtils.updateToMember(updateMemberDTO, member)), HttpStatus.OK);
    }

    @Operation(summary = "Delete Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.DELETE)
    public ResponseEntity<Member> deleteMember(
            @Parameter @RequestBody @Validated Long memberSn) {

        if (Objects.isNull(memberService.findMember(memberSn))) {
            throw new BadRequestException("member was not found");
        }

        memberService.closeMember(memberSn);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}