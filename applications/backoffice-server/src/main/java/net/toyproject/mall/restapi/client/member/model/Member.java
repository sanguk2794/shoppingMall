/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.restapi.client.member.model;

import lombok.Data;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.common.code.MemberStatus;
import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.common.model.embedded.Address;
import net.toyproject.mall.common.model.embedded.Name;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Member implements Serializable {

    private Long memberSn;
    private String emailAddress;
    private String password;
    private String nickName;
    private Name name;
    private Address address;
    private MemberStatus memberStatus;
    private MemberPlatform memberPlatform;
    private YN lockYN;
    private Integer passwordFailureCount;
    private String createdBy;
    private LocalDateTime createdDt;
    private String updatedBy;
    private LocalDateTime updatedDt;
}
