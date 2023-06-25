/**
 * @author sanguk on 2023/06/25
 */

package net.toyproject.mall.prod.model;

import lombok.Getter;
import lombok.Setter;
import net.toyproject.mall.common.model.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "INVENTORY_SEQ_GENERATOR", sequenceName = "INVENTORY_SEQ", allocationSize = 1)
public class Inventory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVENTORY_SEQ_GENERATOR")
    @Column(nullable = false, updatable = false)
    private Long inventorySn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_sn")
    private Prod prod;

    @Column(name = "sku_code", nullable = false, length = 50)
    private String skuCode;

    @Column(name = "qty", precision = 10)
    private Integer qty = Integer.valueOf("0");

}
