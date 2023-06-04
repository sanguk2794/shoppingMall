/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.base.model.embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

public class Name {

    private String firstName;
    private String lastName;
    private String firstNameKana;
    private String lastNameKana;

}