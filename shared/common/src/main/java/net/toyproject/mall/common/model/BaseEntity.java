/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.common.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class BaseEntity implements Serializable {

    private String createdBy;
    private LocalDateTime createdDt;
    private String updatedBy;
    private LocalDateTime updatedDt;

}
