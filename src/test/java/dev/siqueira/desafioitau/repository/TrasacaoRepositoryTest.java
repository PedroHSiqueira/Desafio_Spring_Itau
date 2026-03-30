package dev.siqueira.desafioitau.repository;

import dev.siqueira.desafioitau.dto.TransacaoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrasacaoRepositoryTest {

    private TrasacaoRepository trasacaoRepository;

    @BeforeEach
    void setup() {
        trasacaoRepository = new TrasacaoRepository();
    }

    @Test
    @DisplayName("Save an trasaction on DB in Memory with success")
    void salvarTransacao() {
        TransacaoRequest transacaoRequest = new TransacaoRequest(BigDecimal.ONE, OffsetDateTime.now());

        trasacaoRepository.salvarTransacao(transacaoRequest);

        List<TransacaoRequest> lista = trasacaoRepository.retornaLista();

        assertEquals(1, lista.size());
        assertTrue(lista.contains(transacaoRequest));

    }

    @Test
    @DisplayName("Delete an trasaction from DB in Memory with success")
    void deletarTransacoes() {
        trasacaoRepository.salvarTransacao(new TransacaoRequest(BigDecimal.ONE, OffsetDateTime.now()));
        trasacaoRepository.salvarTransacao(new TransacaoRequest(BigDecimal.ONE, OffsetDateTime.now()));

        trasacaoRepository.deletarTransacoes();

        List<TransacaoRequest> lista = trasacaoRepository.retornaLista();

        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
    }

    @Test
    @DisplayName("Return all the trasaction on DB with success")
    void retornaLista() {
        List<TransacaoRequest> lista = trasacaoRepository.retornaLista();
        lista.add(new TransacaoRequest(BigDecimal.ONE, OffsetDateTime.now()));

        assertEquals(1, trasacaoRepository.retornaLista().size());
    }
}