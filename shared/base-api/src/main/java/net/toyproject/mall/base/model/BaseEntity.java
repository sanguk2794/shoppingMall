/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.base.model;

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
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;
    private static final String DEFAULT_ANONYMOUS_USER = "ANONYMOUS";

    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;
    @Column(name = "created_dt", updatable = false)
    private LocalDateTime createdDt;
    @Column(name = "updated_by", length = 50)
    private String updatedBy;
    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

    @PrePersist
    public void preInsert() {
        if (!StringUtils.hasLength(createdBy)) {
            createdBy = getUserId();
        }

        if (!StringUtils.hasLength(updatedBy)) {
            updatedBy = getUserId();
        }

        LocalDateTime now = LocalDateTime.now();
        if (Objects.isNull(createdDt)) {
            createdDt = now;
        }

        if (Objects.isNull(updatedDt)) {
            updatedDt = now;
        }

    }

    @PreUpdate
    public void preUpdate() {
        updatedBy = getUserId();
        updatedDt = LocalDateTime.now();
    }

    private String getUserId() {
        // TODO SET USER ID
        return DEFAULT_ANONYMOUS_USER;
    }
}
