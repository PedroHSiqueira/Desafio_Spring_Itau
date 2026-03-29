package dev.siqueira.desafioitau.service;

import dev.siqueira.desafioitau.dto.TransacaoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    @DisplayName("Null value in request 'Valor' throws an Exception")
    void validarTransacaoValorNulo() {
        TransacaoRequest request = new TransacaoRequest(null, OffsetDateTime.now());

        assertThrows(IllegalArgumentException.class, () -> transacaoService.validar(request));
    }

    @Test
    @DisplayName("Null value in request 'Data Hora' throws an Exception")
    void validarTransacaoDataHoraNulo() {
        TransacaoRequest request = new TransacaoRequest(BigDecimal.TEN, null);

        assertThrows(IllegalArgumentException.class, () -> transacaoService.validar(request));
    }

    @Test
    @DisplayName("Negative value in request 'Valor' throws an Exception")
    void validarTransacaoValorNegativo() {
        TransacaoRequest request = new TransacaoRequest(BigDecimal.valueOf(-10.0), OffsetDateTime.now());

        assertThrows(IllegalArgumentException.class, () -> transacaoService.validar(request));
    }

    @Test
    @DisplayName("Out of date value in request 'Data Hora' throws an Exception")
    void validarTransacaoDataFutura() {
        TransacaoRequest request = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now().plusDays(30));

        assertThrows(IllegalArgumentException.class, () -> transacaoService.validar(request));
    }

    @Test
    @DisplayName("date value in request 'Data Hora' not throws an Exception")
    void validarTransacaoDataNaoFutura() {
        TransacaoRequest request = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now());

        assertDoesNotThrow(() -> transacaoService.validar(request));
    }
}