/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.member;

import io.swagger.annotations.ApiParam;
import net.toyproject.mall.api.config.provider.JwtTokenProvider;
import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.api.member.dto.PasswordCredentialDTO;
import net.toyproject.mall.api.member.dto.TokenDTO;
import net.toyproject.mall.api.util.MemberValidateUtils;
import net.toyproject.mall.member.model.Member;
import net.toyproject.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController(value = "v1/token")
public class TokenApi {

    private static final int LOCK_PASSWORD_FAILURE_COUNT = 5;

    @Autowired
    MemberService memberService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @PostMapping("tokens")
    public ResponseEntity<TokenDTO> login(
            @RequestBody @ApiParam(required = true) @Validated PasswordCredentialDTO passwordCredentialDTO) {

        MemberValidateUtils.loginMemberValidate(passwordCredentialDTO);

        final Member member = memberService.findMemberByEmailAddress(passwordCredentialDTO.getEmailAddress());
        if (Objects.isNull(member)) {
            throw new BadRequestException();
        }

        if (passwordCredentialDTO.getMemberPlatform() != member.getMemberPlatform()) {
            throw new BadRequestException();
        }

        String dbPassword = member.getPassword();
        if (invalidPassword(passwordCredentialDTO, dbPassword)) {
            memberService.increasePasswordVerifyFailureCnt(member.getMemberSn(), LOCK_PASSWORD_FAILURE_COUNT);
            throw new BadRequestException();
        }

        return ResponseEntity.ok(createToken(member));
    }

    public TokenDTO createToken(Member member) {
        return new TokenDTO(
                jwtTokenProvider.createToken(member.getEmailAddress()), "bearer");
    }

    private static boolean invalidPassword(PasswordCredentialDTO passwordCredentialDTO, String dbPassword) {
        return !passwordCredentialDTO.getPassword().equalsIgnoreCase(dbPassword);
    }
}
