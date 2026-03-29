package dev.siqueira.desafioitau.service;

import dev.siqueira.desafioitau.Entity.Estatistica;
import dev.siqueira.desafioitau.config.EstatisticaProperties;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EstatisticaServiceTest {

    @InjectMocks
    private EstatisticaService estatisticaService;

    @Mock
    private EstatisticaProperties estatisticaProperties;

    @Test
    @DisplayName("Generate stats with a empty list")
    void gerarEstatisticasComListaVazia(){
        List<TransacaoRequest> listaTransacoes = new ArrayList<>();

        Estatistica estatistica = estatisticaService.gerarEstatistica(listaTransacoes);

        assertEquals(0L, estatistica.getCount());
        assertEquals(0.0D, estatistica.getSum());
        assertEquals(0.0D, estatistica.getAvg());
        assertEquals(0.0D, estatistica.getMin());
        assertEquals(0.0D, estatistica.getMax());
    }

    @Test
    @DisplayName("Generate stats with a list containing multiples itens ")
    void gerarEstatisticasComListaPreenchida(){
        TransacaoRequest transacaoRequest = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now());

        List<TransacaoRequest> listaTransacoes = new ArrayList<>();
        listaTransacoes.add(transacaoRequest);

        Estatistica estatistica = estatisticaService.gerarEstatistica(listaTransacoes);

        assertEquals(1L, estatistica.getCount());
    }

    @Test
    @DisplayName("Generate stats with a list containing multiples itens but with wrong date ")
    void gerarEstatisticasComListaPreenchidaComDataInvalida(){
        TransacaoRequest transacaoRequest = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now().minusDays(30));

        List<TransacaoRequest> listaTransacoes = new ArrayList<>();
        listaTransacoes.add(transacaoRequest);

        Estatistica estatistica = estatisticaService.gerarEstatistica(listaTransacoes);

        assertEquals(0L, estatistica.getCount());
    }

}