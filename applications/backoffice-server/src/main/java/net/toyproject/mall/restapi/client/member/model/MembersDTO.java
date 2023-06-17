/**
 * @author sanguk on 2023/06/13
 */

package net.toyproject.mall.restapi.client.member.model;

import lombok.Data;

import java.util.List;

@Data
public class MembersDTO {

    Integer totalCount;
    private List<Member> members;

}
