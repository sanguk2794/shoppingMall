/**
 * @author sanguk on 2023/06/03
 */

package net.toyproject.mall.member.model;


import lombok.Getter;
import lombok.Setter;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.common.code.MemberStatus;
import net.toyproject.mall.common.code.YN;
import net.toyproject.mall.common.model.BaseEntity;
import net.toyproject.mall.common.model.embedded.Address;
import net.toyproject.mall.common.model.embedded.Name;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Member")
@Table(name = "MEMBER")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", allocationSize = 1)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(nullable = false, updatable = false)
    private Long memberSn;

    @Column(nullable = false, length = 30)
    private String emailAddress;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickName;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    private MemberPlatform memberPlatform;

    @Enumerated(EnumType.STRING)
    private YN lockYN;

    @Column(nullable = false)
    private Integer passwordFailureCount;
}