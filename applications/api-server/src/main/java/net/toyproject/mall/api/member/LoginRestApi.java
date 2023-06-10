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
import net.toyproject.mall.api.util.MemberValidateUtils;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            tokenDTO.setResponseCode("ST002");
            return ResponseEntity.ok(tokenDTO);
        }

        if (loginParam.getMemberPlatform() != member.getMemberPlatform()) {
            tokenDTO.setResponseCode("ST002");
            return ResponseEntity.ok(tokenDTO);
        }

        String dbPassword = member.getPassword();
        if (invalidPassword(loginParam, dbPassword)) {
            memberService.increasePasswordVerifyFailureCnt(member.getMemberSn(), LOCK_PASSWORD_FAILURE_COUNT);
            tokenDTO.setResponseCode("ST002");
            return ResponseEntity.ok(tokenDTO);
        }

        if (memberService.isLockMember(member.getMemberSn())) {
            tokenDTO.setResponseCode("ST003");
            return ResponseEntity.ok(tokenDTO);
        }

        return ResponseEntity.ok(setTokenDTO(tokenDTO, member));
    }

    public TokenDTO setTokenDTO(TokenDTO tokenDTO, Member member) {
        tokenDTO.setResponseCode("ST001");
        tokenDTO.setMemberSn(member.getMemberSn());

        final String accessToken = jwtTokenProvider.createToken(JwtTokenProvider.TokenType.Access, member.getEmailAddress());
        tokenDTO.setAccessToken(accessToken);
        tokenDTO.setAccessTokenExpDt(jwtTokenProvider.getExpiration(JwtTokenProvider.TokenType.Access, accessToken));

        final String refreshToken = jwtTokenProvider.createToken(JwtTokenProvider.TokenType.Refresh, member.getEmailAddress());
        tokenDTO.setRefreshToken(refreshToken);
        tokenDTO.setRefreshTokenExpDt(jwtTokenProvider.getExpiration(JwtTokenProvider.TokenType.Refresh, refreshToken));

        return tokenDTO;
    }

    private static boolean invalidPassword(LoginParam loginParam, String dbPassword) {
        return !loginParam.getPassword().equalsIgnoreCase(dbPassword);
    }

    @Operation(summary = "Refresh Login Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Refresh Login Token"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @PostMapping("/tokens/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody @Validated RefreshTokenParam refreshTokenParam) {
        final TokenDTO tokenDTO = new TokenDTO();

        if (jwtTokenProvider.validateToken(JwtTokenProvider.TokenType.Refresh, refreshTokenParam.getRefreshToken())) {
            tokenDTO.setResponseCode("ST002");
            return ResponseEntity.ok(tokenDTO);
        }

        return ResponseEntity.ok(
                setTokenDTO(tokenDTO, memberService.findMember(
                        jwtTokenProvider.getMemberSn(
                                JwtTokenProvider.TokenType.Refresh, refreshTokenParam.getRefreshToken()))));
    }
}
