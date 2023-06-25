package net.toyproject.mall.restapi.client.member;

import feign.Headers;
import feign.RequestLine;
import net.toyproject.mall.back.controller.guest.model.TokenDTO;
import net.toyproject.mall.back.controller.guest.model.param.LoginParam;
import net.toyproject.mall.back.controller.guest.model.param.RefreshTokenParam;

@Headers({ "Content-Type: application/json" })
public interface LoginApi {

    @RequestLine("POST /v1/member/tokens")
    TokenDTO login(LoginParam loginParam);

    @RequestLine("POST /v1/member/tokens/refresh")
    TokenDTO refreshToken(RefreshTokenParam RefreshInfo);

}
