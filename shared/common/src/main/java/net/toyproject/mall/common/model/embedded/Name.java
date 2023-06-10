/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.common.model.embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
public class Name {

    private String firstName;
    private String lastName;

}