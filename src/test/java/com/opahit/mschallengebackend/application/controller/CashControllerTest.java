package com.opahit.mschallengebackend.application.controller;

import com.opahit.mschallengebackend.application.response.CashConciliationResponse;
import com.opahit.mschallengebackend.domain.service.CashService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CashController.class)
class CashControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CashService cashService;

    @Test
    void givenEmptyJson_whenPostCash_thenReturnBadRequest() throws Exception {
        String jsonRequest = """
                {}""";

        mvc.perform(post("/api/v1/cash")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("Requisição inválida"))
                .andExpect(jsonPath("$.detailMessages").value(containsInAnyOrder("value is required and greather than zero",
                        "type is required", "clientName is required")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenBodyWithTypeInvalidValueInvalidDateInvalid_whenPostCash_thenReturnBadRequest() throws Exception {
        String jsonRequest = """
                {"clientName": "teste", "type":"TESTE", "value": 0.00, "date": "1234"}""";

        mvc.perform(post("/api/v1/cash")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("Requisição inválida"))
                .andExpect(jsonPath("$.detailMessages").value(containsInAnyOrder("date 1234 is not valid date, format : yyyy-MM-dd HH:mm:ss",
                        "type TESTE not exist, possible values: [DEBIT, CREDIT]", "value is required and greather than zero")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenBodyComplete_whenPostCash_thenReturnCreated() throws Exception {
        String jsonRequest = """
                {"clientName": "teste", "type":"DEBIT", "value": 5.00, "date": "2023-01-01 11:50:00"}""";

        mvc.perform(post("/api/v1/cash")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void givenInvalidDate_whenGetCashConciliation_thenReturnBadRequest() throws Exception {

        mvc.perform(get("/api/v1/cash/conciliation?date=1234"))
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("Requisição inválida"))
                .andExpect(jsonPath("$.detailMessages").value(containsInAnyOrder("date 1234 is not valid date, format : yyyy-MM-dd")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenDateValid_whenGetCashConciliation_thenReturnOk() throws Exception {
        Mockito.when(cashService.getCashConciliation("2022-01-01")).thenReturn(new CashConciliationResponse(15.00));

        mvc.perform(get("/api/v1/cash/conciliation?date=2022-01-01"))
                .andExpect(jsonPath("$.value").value(15.00))
                .andExpect(status().isOk());
    }

}
