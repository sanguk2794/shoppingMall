/**
 * @author sanguk on 2023/06/25
 */

package net.toyproject.mall.prod.model;

import lombok.Getter;
import lombok.Setter;
import net.toyproject.mall.common.code.ProdColor;
import net.toyproject.mall.common.code.ProdSize;
import net.toyproject.mall.common.model.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "PROD_SEQ_GENERATOR", sequenceName = "PROD_SEQ", allocationSize = 1)
public class Prod extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_SEQ_GENERATOR")
    @Column(nullable = false, updatable = false)
    private Long prodSn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_prod_sn")
    private OnlineProd onlineProd;

    @OneToOne(mappedBy = "prod", fetch = FetchType.LAZY)
    private Inventory inventory;

    @Column(precision = 15, scale = 5)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProdColor prodColor;

    @Enumerated(EnumType.STRING)
    private ProdSize prodSize;

}