package dev.siqueira.desafioitau.repository;

import dev.siqueira.desafioitau.dto.TransacaoRequest;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrasacaoRepository {
    List<TransacaoRequest> listaTransacoes = new ArrayList<>();

    public void salvarTransacao(TransacaoRequest transacao){
        listaTransacoes.add(transacao);
    }

    public void limparTransacoes(TransacaoRequest transacao){}

    public void deletarTransacoes(){
        listaTransacoes.clear();
    }

    public List<TransacaoRequest> retornaLista(){
        return listaTransacoes;
    }
}
