package com.opahit.mschallengebackend.application.controller;

import com.opahit.mschallengebackend.application.request.CashRequest;
import com.opahit.mschallengebackend.application.request.mapper.CashRequestMapper;
import com.opahit.mschallengebackend.application.response.CashConciliationResponse;
import com.opahit.mschallengebackend.domain.dto.CashDTO;
import com.opahit.mschallengebackend.domain.service.CashService;
import com.opahit.mschallengebackend.infrastructure.configuration.annotations.ValidateLocalDate;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.info.Info;

@RequestMapping("api/v1/cash")
@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "API - MS Cash Control",
                version = "1.0",
                description = "sistema de controle de fluxo de caixa"
        )
)
@Validated
public class CashController {
    private final CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @Operation(
            summary = "Cadastra uma operação de crédito ou debito para o estabelecimento",
            operationId = "postCash",
            responses = {
               @ApiResponse(responseCode = "201", description = "Sucesso operação cadastrada"),
               @ApiResponse(responseCode = "400", description = "Requisição inválida",
                       content = {@Content(schema = @Schema(example = "{\"timestamp\":\"2023-05-24T11:36:31.896Z\"," +
                               "\"code\":\"400\",\"message\":\"Requisição inválida\", " +
                               "\"detailMessages\": [\"type is required\"], \"path\":\"http://localhost:8080/api/v1/cash\"}"))}),
               @ApiResponse(responseCode = "500", description = "Erro Inesperado",
                            content = {@Content(schema = @Schema(example = "{\"timestamp\":\"2023-05-24T11:36:31.896Z\"," +
                                    "\"code\":\"500\",\"message\":\"Erro Inesperado\", \"path\":\"http://localhost:8080/api/v1/cash\"}"))})
            }
    )
    @PostMapping(
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public void postCash(@Parameter(description = "corpo da requisição", required = true) @Valid @RequestBody CashRequest cashRequest) {
        CashDTO cashDTO = CashRequestMapper.convertIntoCashDTO(cashRequest);
        cashService.createCash(cashDTO);
    }

    @Operation(
            summary = "Retorna o consolidado baseado em uma data específica ou do dia atual",
            operationId = "getConsiliation",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso", content = {@Content(schema = @Schema(implementation = CashConciliationResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = {@Content(schema = @Schema(example = "{\"timestamp\":\"2023-05-24T11:36:31.896Z\"," +
                                    "\"code\":\"400\",\"message\":\"Requisição inválida\", " +
                                    "\"detailMessages\": [\"date 1234 is not valid date, format : yyyy-MM-dd\"], \"path\":\"http://localhost:8080/api/v1/cash/conciliation\"}"))}),
                    @ApiResponse(responseCode = "500", description = "Erro Inesperado",
                            content = {@Content(schema = @Schema(example = "{\"timestamp\":\"2023-05-24T11:36:31.896Z\"," +
                                    "\"code\":\"500\",\"message\":\"Erro Inesperado\", \"path\":\"http://localhost:8080/api/v1/cash/conciliation\"}"))})
            }
    )
    @GetMapping(
            value = "/conciliation",
            produces = {"application/json"}
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CashConciliationResponse> getCashConciliation(@Parameter(description = "parametro de data") @RequestParam(name = "date", required = false) @ValidateLocalDate String date) {
        CashConciliationResponse cashConciliationResponse = cashService.getCashConciliation(date);
        return ResponseEntity.ok(cashConciliationResponse);
    }


}
