/**
 * @author sanguk on 2023/06/25
 */

package net.toyproject.mall.prod.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "ONLINE_PROD_SEQ_GENERATOR", sequenceName = "ONLINE_PROD_SEQ", allocationSize = 1)
public class OnlineProd {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ONLINE_SEQ_GENERATOR")
    @Column(nullable = false, updatable = false)
    private Long onlineProdSn;

    @OneToMany(mappedBy = "onlineProd", fetch = FetchType.LAZY)
    private List<Prod> prods = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Long coSn;

    @Column(nullable = false, length = 200)
    private String prodName;

    private LocalDateTime saleStartDt;

    private LocalDateTime saleEndDt;

}
