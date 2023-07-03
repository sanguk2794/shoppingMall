/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.toyproject.mall.api.config.provider.JwtTokenProvider;
import net.toyproject.mall.api.member.dto.LoginParam;
import net.toyproject.mall.api.member.dto.RefreshTokenParam;
import net.toyproject.mall.api.member.dto.TokenDTO;
import net.toyproject.mall.api.member.util.MemberValidateUtils;
import net.toyproject.mall.common.code.LoginProcessStatusCode;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/v1/member")
public class LoginRestApi {

    private static final int LOCK_PASSWORD_FAILURE_COUNT = 5;

    @Autowired
    MemberService memberService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Operation(summary = "Get Login Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Login Token"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @PostMapping("/tokens")
    public ResponseEntity<TokenDTO> login(
            @RequestBody @Validated LoginParam loginParam) {

        final TokenDTO tokenDTO = new TokenDTO();
        MemberValidateUtils.loginMemberValidate(loginParam);

        final Member member = memberService.findMemberByEmailAddress(loginParam.getEmailAddress());
        if (Objects.isNull(member)) {
            tokenDTO.setResponseCode(LoginProcessStatusCode.Empty);
            return ResponseEntity.ok(tokenDTO);
        }

        if (loginParam.getMemberPlatform() != member.getMemberPlatform()) {
            tokenDTO.setResponseCode(LoginProcessStatusCode.InvalidPlatform);
            return ResponseEntity.ok(tokenDTO);
        }

        String dbPassword = member.getPassword();
        if (!bCryptPasswordEncoder.matches(loginParam.getPassword(), dbPassword)) {
            memberService.increasePasswordVerifyFailureCnt(member.getMemberSn(), LOCK_PASSWORD_FAILURE_COUNT);
            tokenDTO.setResponseCode(LoginProcessStatusCode.Invalid);
            return ResponseEntity.ok(tokenDTO);
        }

        if (memberService.isLockMember(member.getMemberSn())) {
            tokenDTO.setResponseCode(LoginProcessStatusCode.Locked);
            return ResponseEntity.ok(tokenDTO);
        }

        return ResponseEntity.ok(
                getLoginSuccessTokenDTO(tokenDTO, member));
    }

    public TokenDTO getLoginSuccessTokenDTO(TokenDTO tokenDTO, Member member) {
        tokenDTO.setResponseCode(LoginProcessStatusCode.Success);
        tokenDTO.setMemberSn(member.getMemberSn());
        tokenDTO.setFirstName(member.getName().getFirstName());
        tokenDTO.setLastName(member.getName().getLastName());

        final String accessToken = jwtTokenProvider.createToken(member.getEmailAddress());
        tokenDTO.setAccessToken(accessToken);
        tokenDTO.setAccessTokenExpDt(jwtTokenProvider.getExpiration(accessToken));

        return tokenDTO;
    }

    @Operation(summary = "Refresh Login Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Refresh Login Token"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @PostMapping("/tokens/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody @Validated RefreshTokenParam refreshTokenParam) {
        final TokenDTO tokenDTO = new TokenDTO();

        if (jwtTokenProvider.validateToken(refreshTokenParam.getRefreshToken())) {
            tokenDTO.setResponseCode(LoginProcessStatusCode.Invalid);
            return ResponseEntity.ok(tokenDTO);
        }

        return ResponseEntity.ok(
                getLoginSuccessTokenDTO(tokenDTO, memberService.findMember(
                        jwtTokenProvider.getMemberSn(refreshTokenParam.getRefreshToken()))));
    }
}
