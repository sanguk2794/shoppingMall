/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.common.code;

import lombok.Getter;

@Getter
public enum LoginProcessStatusCode {
    Success("成功です。"),

    Empty("必須です。"),

    Invalid("メールアドレス又は暗証番号が間違っています。"),
    InvalidPlatform("プラットホームが間違っています。"),

    Locked("ロックされたアカウントです。");

    private final String message;

    LoginProcessStatusCode(String message) {
        this.message = message;
    }
}
