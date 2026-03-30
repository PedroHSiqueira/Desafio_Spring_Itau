package dev.siqueira.desafioitau.controller;

import dev.siqueira.desafioitau.Entity.Estatistica;
import dev.siqueira.desafioitau.config.EstatisticaProperties;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import dev.siqueira.desafioitau.service.EstatisticaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EstatisticasController.class)
class EstatisticasControllerTest {

    @MockitoBean
    private EstatisticaService estatisticaService;

    @MockitoBean
    private TrasacaoRepository trasacaoRepository;

    @MockitoBean
    private EstatisticaProperties estatisticaProperties;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void recuperarEstatisticas() throws Exception {
        List<TransacaoRequest> lista = List.of(new TransacaoRequest(BigDecimal.ONE, OffsetDateTime.now()));
        Estatistica estatistica = new Estatistica(1L, 1.0, 1.0, 1.0, 1.0);

        Mockito.when(trasacaoRepository.retornaLista())
                .thenReturn(lista);

        Mockito.when(estatisticaService.gerarEstatistica(lista))
                .thenReturn(estatistica);

        mockMvc.perform(MockMvcRequestBuilders.get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.sum").value(1.0))
                .andExpect(jsonPath("$.avg").value(1.0))
                .andExpect(jsonPath("$.max").value(1.0))
                .andExpect(jsonPath("$.min").value(1.0));

        Mockito.verify(trasacaoRepository, Mockito.times(1)).retornaLista();
        Mockito.verify(estatisticaService, Mockito.times(1)).gerarEstatistica(lista);
    }
}