package dev.siqueira.desafioitau.controller;

import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import dev.siqueira.desafioitau.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    @MockitoBean
    private TransacaoService transacaoService;

    @MockitoBean
    private TrasacaoRepository trasacaoRepository;

    @MockitoBean
    private TransacaoRequest transacaoRequest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        transacaoRequest = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now());
    }

    @Test
    @DisplayName("Create an Transaction with success")
    void createTrasactionSuccessfully() throws Exception {
        Mockito.doNothing().when(transacaoService).validar(Mockito.any());
        Mockito.doNothing().when(trasacaoRepository).salvarTransacao(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Dont't Create an Transaction because of an IllegalArgument")
    void createTrasactionIllegalArgument() throws Exception {

        Mockito.doThrow(new IllegalArgumentException())
                .when(transacaoService).validar(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequest)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Dont't Create an Transaction because of an Server Error")
    void createTrasactionException() throws Exception {

        Mockito.doThrow(new NullPointerException())
                .when(transacaoService).validar(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Delete an Transaction with success")
    void deleteTrasactionwithSuccess() throws Exception {
        Mockito.doNothing().when(trasacaoRepository).deletarTransacoes();

        mockMvc.perform(MockMvcRequestBuilders.delete("/transacao"))
                .andExpect(status().isOk());
    }
}