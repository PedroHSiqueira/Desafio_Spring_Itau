package dev.siqueira.desafioitau.controller;

import dev.siqueira.desafioitau.Entity.Trasacao;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import dev.siqueira.desafioitau.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
@Tag(name="Transações", description = "Endpoints para criação e deleção de Transações")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final TrasacaoRepository trasacaoRepository;

    public TransacaoController(TransacaoService transacaoService, TrasacaoRepository trasacaoRepository) {
        this.transacaoService = transacaoService;
        this.trasacaoRepository = trasacaoRepository;
    }

    @PostMapping
    @Operation(summary = "Cria transações", description = "Recebe uma transação valida e adiciona na lista")
    @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso!")
    @ApiResponse(responseCode = "422", description = "Transação mal formatada!")
    @ApiResponse(responseCode = "400", description = "Erro no servidor")
    public ResponseEntity<Trasacao> adicionar(@RequestBody TransacaoRequest trasacao){
        try {
            transacaoService.validar(trasacao);
            trasacaoRepository.salvarTransacao(trasacao);

            log.info("Transação criada com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalArgumentException e) {
            log.error("Ocorreu um erro na passagem dos parametros para formularios! tente novamente");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception e) {
            log.error("Ocorreu um erro interno no sistema! tente novamente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(){
        trasacaoRepository.deletarTransacoes();

        return ResponseEntity.ok().build();
    }
}
