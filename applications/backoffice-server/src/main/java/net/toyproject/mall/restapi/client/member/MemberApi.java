/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.restapi.client.member;


import feign.Headers;
import feign.Param;
import feign.RequestLine;
import net.toyproject.mall.restapi.client.member.model.Member;
import net.toyproject.mall.restapi.client.member.model.RegisterMemberDTO;
import net.toyproject.mall.restapi.client.member.model.ResetPasswordDTO;
import net.toyproject.mall.restapi.client.member.model.UpdateMemberDTO;

@Headers({ "Content-Type: application/json" })
public interface MemberApi {

    @RequestLine("POST /v1/member/members")
    Member registerMember(RegisterMemberDTO registerMemberDTO);

    @RequestLine("GET /v1/member/members/{memberSn}")
    Member getMember(@Param("memberSn") Long memberSn);

    @RequestLine("PUT /v1/member/members")
    void updateMember(UpdateMemberDTO updateMemberDTO);

    @RequestLine("DELETE /v1/member/members/{memberSn}")
    void deleteMember(@Param("memberSn") Long memberSn);

    @RequestLine("GET /v1/member/members?emailAddress={emailAddress}")
    Member getMemberByEmailAddress(@Param("emailAddress") String emailAddress);

    @RequestLine("PATCH /v1/member/members")
    void resetPassword(ResetPasswordDTO resetPasswordDTO);

}
