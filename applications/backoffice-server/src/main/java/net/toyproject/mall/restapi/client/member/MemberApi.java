/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.restapi.client.member;


import feign.Headers;
import feign.Param;
import feign.RequestLine;
import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.restapi.client.member.model.*;

import java.util.List;

@Headers({ "Content-Type: application/json" })
public interface MemberApi {

    @RequestLine("POST /v1/member/members")
    Member registerMember(RegisterMemberDTO registerMemberDTO);

    @RequestLine("GET /v1/member/members/{memberSn}")
    Member getMember(@Param("memberSn") Long memberSn);

    @RequestLine("GET /v1/member/members/list?offset={offset}&limit={limit}&orderBy={orderBy}")
    MembersDTO getMembers(@Param("offset") Integer offset,
                          @Param("limit") Integer limit,
                          @Param("orderBy") OrderBy orderBy);

    @RequestLine("PUT /v1/member/members")
    void updateMember(UpdateMemberDTO updateMemberDTO);

    @RequestLine("DELETE /v1/member/members/{memberSn}")
    void deleteMember(@Param("memberSn") Long memberSn);

    @RequestLine("GET /v1/member/members?emailAddress={emailAddress}")
    Member getMemberByEmailAddress(@Param("emailAddress") String emailAddress);

    @RequestLine("PATCH /v1/member/members")
    void resetPassword(ResetPasswordDTO resetPasswordDTO);

}
