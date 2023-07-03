/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.api.member.dto.MembersDTO;
import net.toyproject.mall.api.member.dto.RegisterMemberDTO;
import net.toyproject.mall.api.member.dto.ResetPasswordDTO;
import net.toyproject.mall.api.member.dto.UpdateMemberDTO;
import net.toyproject.mall.api.member.util.MemberUtils;
import net.toyproject.mall.api.member.util.MemberValidateUtils;
import net.toyproject.mall.common.code.OrderBy;
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
            @ApiResponse(responseCode = "201", description = "Register Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.POST)
    public ResponseEntity<Member> registerMember(
            @Parameter @RequestBody @Validated RegisterMemberDTO registerMemberDTO) {

        MemberValidateUtils.registerMemberValidate(registerMemberDTO);

        return new ResponseEntity<>(
                memberService.createMember(MemberUtils.registerToMember(registerMemberDTO)), HttpStatus.CREATED);
    }

    @Operation(summary = "Get Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members/{memberSn}", method = RequestMethod.GET)
    public ResponseEntity<Member> getMember(
            @Parameter @RequestParam @Validated Long memberSn) {

        final Member member = memberService.findMember(memberSn);
        if (Objects.isNull(member)) {
            throw new BadRequestException("member was not found");
        }

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Operation(summary = "Get Members")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members/list", method = RequestMethod.GET)
    public ResponseEntity<MembersDTO> getMembers(
            @Parameter @RequestParam @Validated Integer offset,
            @Parameter @RequestParam @Validated Integer limit,
            @Parameter @RequestParam @Validated OrderBy orderBy) {

        MembersDTO membersDTO = new MembersDTO();
        membersDTO.setTotalCount(memberService.getMembersCount());
        membersDTO.setMembers(memberService.findMembers(offset, limit, orderBy));

        return new ResponseEntity<>(membersDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMember(
            @Parameter @RequestBody @Validated UpdateMemberDTO updateMemberDTO) {

        MemberValidateUtils.updateMemberValidate(updateMemberDTO);

        final Member member = memberService.findMember(updateMemberDTO.getMemberSn());
        if (Objects.isNull(member)) {
            throw new BadRequestException("member was not found");
        }

        memberService.updateMember(
                MemberUtils.updateToMember(updateMemberDTO, member));

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members/{memberSn}", method = RequestMethod.DELETE)
    public ResponseEntity<Member> deleteMember(
            @Parameter @RequestBody @Validated Long memberSn) {

        if (Objects.isNull(memberService.findMember(memberSn))) {
            throw new BadRequestException("member was not found");
        }

        memberService.closeMember(memberSn);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Member by email address")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Member"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.GET)
    public ResponseEntity<Member> getMemberByEmailAddress(
            @Parameter @RequestParam @Validated String emailAddress) {

        final Member member = memberService.findMemberByEmailAddress(emailAddress);
        if (Objects.isNull(member)) {
            throw new BadRequestException("member was not found");
        }

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @Operation(summary = "Reset Member Password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reset Member Password"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/members", method = RequestMethod.PATCH)
    public ResponseEntity<Member> resetPassword(
            @Parameter @RequestBody @Validated ResetPasswordDTO resetPasswordDTO) {

        MemberValidateUtils.resetPasswordValidate(resetPasswordDTO);

        if (Objects.isNull(memberService.findMember(resetPasswordDTO.getMemberSn()))) {
            throw new BadRequestException("member was not found");
        }

        memberService.updatePassword(
                resetPasswordDTO.getMemberSn(), resetPasswordDTO.getPassword());

        return ResponseEntity.noContent().build();
    }
}
