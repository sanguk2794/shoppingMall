package net.toyproject.mall.api.co.dto;

import lombok.Data;
import net.toyproject.mall.co.model.Co;

import java.util.List;

@Data
public class CosDTO {

    Integer totalCount;
    private List<Co> cos;

}
