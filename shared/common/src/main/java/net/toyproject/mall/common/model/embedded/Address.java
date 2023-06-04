/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.common.model.embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
public class Address {

    private String country;
    private String zipCode;
    private String area;
    private String address1;
    private String address2;
    private String address3;

}