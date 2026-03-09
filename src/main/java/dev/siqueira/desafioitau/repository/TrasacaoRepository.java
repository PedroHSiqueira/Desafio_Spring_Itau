package dev.siqueira.desafioitau.repository;

import dev.siqueira.desafioitau.dto.TransacaoRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrasacaoRepository {
    List<TransacaoRequest> listaTransacoes = new ArrayList<>();

    public void salvarTransacao(TransacaoRequest transacao){
        listaTransacoes.add(transacao);
    }

    public void limparTransacao(TransacaoRequest transacao){

    }

    public void deletarTransacao(){
        listaTransacoes.clear();
    }
}
