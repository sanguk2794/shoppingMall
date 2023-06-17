package net.toyproject.mall.api.member.dto;

import lombok.Data;
import net.toyproject.mall.member.model.Member;

import java.util.List;

@Data
public class MembersDTO {

    Integer totalCount;
    private List<Member> members;

}
