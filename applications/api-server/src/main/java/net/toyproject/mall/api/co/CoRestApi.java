/**
 * @author sanguk on 2023/06/04
 */

package net.toyproject.mall.api.co;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.toyproject.mall.api.co.dto.CosDTO;
import net.toyproject.mall.api.co.dto.RegisterCoDTO;
import net.toyproject.mall.api.co.dto.UpdateCoDTO;
import net.toyproject.mall.api.co.utils.CoValidateUtils;
import net.toyproject.mall.api.exception.BadRequestException;
import net.toyproject.mall.co.model.Co;
import net.toyproject.mall.co.service.CoService;
import net.toyproject.mall.common.code.OrderBy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v1/co")
public class CoRestApi {

    @Autowired
    CoService coService;

    @Operation(summary = "Register co")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Register Co"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/cos", method = RequestMethod.POST)
    public ResponseEntity<Co> registerCo(
            @Parameter @RequestBody @Validated RegisterCoDTO registerCoDTO) {

        CoValidateUtils.registerCoValidate(registerCoDTO);

        final Co co = new Co();
        BeanUtils.copyProperties(registerCoDTO, co);

        return new ResponseEntity<>(
                coService.createCo(co), HttpStatus.CREATED);
    }

    @Operation(summary = "Get co")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get co"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/cos/{coSn}", method = RequestMethod.GET)
    public ResponseEntity<Co> getCo(
            @Parameter @RequestParam @Validated Long coSn) {

        final Co co = coService.findCo(coSn);
        if (Objects.isNull(co)) {
            throw new BadRequestException("co was not found");
        }

        return new ResponseEntity<>(co, HttpStatus.OK);
    }

    @Operation(summary = "Get cos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Co"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/cos/list", method = RequestMethod.GET)
    public ResponseEntity<CosDTO> getCos(
            @Parameter @RequestParam @Validated Integer offset,
            @Parameter @RequestParam @Validated Integer limit,
            @Parameter @RequestParam @Validated OrderBy orderBy) {

        CosDTO cosDTO = new CosDTO();
        cosDTO.setTotalCount(coService.getCosCount());
        cosDTO.setCos(coService.findCos(offset, limit, orderBy));

        return new ResponseEntity<>(cosDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update co")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Co"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/cos", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCo(
            @Parameter @RequestBody @Validated UpdateCoDTO updateCoDTO) {

        CoValidateUtils.updateCoValidate(updateCoDTO);

        if (Objects.isNull(coService.findCo(updateCoDTO.getCoSn()))) {
            throw new BadRequestException("co was not found");
        }

        final Co co = new Co();
        BeanUtils.copyProperties(updateCoDTO, co);

        coService.updateCo(co);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete co")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Co"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "500", description = "Internal Error")
    })
    @RequestMapping(value="/co/{coSn}", method = RequestMethod.DELETE)
    public ResponseEntity<Co> deleteCo(
            @Parameter @RequestBody @Validated Long coSn) {

        if (Objects.isNull(coService.findCo(coSn))) {
            throw new BadRequestException("co was not found");
        }

        coService.deleteCo(coSn);

        return ResponseEntity.noContent().build();
    }
}
