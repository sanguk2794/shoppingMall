/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.member.repository;

import net.toyproject.mall.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
