/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.back.controller.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Seo implements Serializable {

    private String title;
    private String description;
    private String keyword;

}
