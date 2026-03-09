package dev.siqueira.desafioitau.service;

import dev.siqueira.desafioitau.dto.TransacaoRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    public void validar(TransacaoRequest transacaoRequest){
        if (transacaoRequest.valor() == null){
            throw new IllegalArgumentException("Erro: Valor deve ser passado para a requisição!");
        }

        if (transacaoRequest.dataHora() == null){
            throw new IllegalArgumentException("Erro: Data e Hora deve ser passado para a requisição!");
        }

        if (transacaoRequest.valor().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Erro: O Valor não deve ser menor que Zero!");
        }

        if (transacaoRequest.dataHora().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Erro: a data não pode ser no futuro!");
        }
    }
}
