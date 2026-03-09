package dev.siqueira.desafioitau.controller;

import dev.siqueira.desafioitau.Entity.Trasacao;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import dev.siqueira.desafioitau.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final TrasacaoRepository trasacaoRepository;

    public TransacaoController(TransacaoService transacaoService, TrasacaoRepository trasacaoRepository) {
        this.transacaoService = transacaoService;
        this.trasacaoRepository = trasacaoRepository;
    }

    @PostMapping
    public ResponseEntity<Trasacao> adicionar(@RequestBody TransacaoRequest trasacao){
        try {
            transacaoService.validar(trasacao);
            trasacaoRepository.salvarTransacao(trasacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
