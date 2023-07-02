/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.co.repository;

import net.toyproject.mall.co.model.Co;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoRepository extends JpaRepository<Co, Long> {

}
