/**
 * @author sanguk on 2023/06/25
 */

package net.toyproject.mall.co.model;

import lombok.Getter;
import lombok.Setter;
import net.toyproject.mall.common.model.BaseEntity;
import net.toyproject.mall.common.model.embedded.Name;
import net.toyproject.mall.common.model.embedded.PhoneNumber;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
@SequenceGenerator(name = "CO_SEQ_GENERATOR", sequenceName = "CO_SEQ", allocationSize = 1)
public class Co extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CO_SEQ_GENERATOR")
    @Column(nullable = false, updatable = false)
    private Long coSn;

    @Column(nullable = false, length = 200)
    private String coName;

    @Column(nullable = false, length = 30)
    private String emailAddress;

    @Embedded
    private PhoneNumber faxNumber;

}
