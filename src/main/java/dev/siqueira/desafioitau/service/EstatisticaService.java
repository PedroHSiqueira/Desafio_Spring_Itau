package dev.siqueira.desafioitau.service;

import dev.siqueira.desafioitau.Entity.Estatistica;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EstatisticaService {

    private final TrasacaoRepository trasacaoRepository;

    public EstatisticaService(TrasacaoRepository trasacaoRepository) {
        this.trasacaoRepository = trasacaoRepository;
    }

    public Estatistica gerarEstatistica(List<TransacaoRequest> listTransacoes){
        OffsetDateTime horaInicial = OffsetDateTime.now().minusDays(60);
        List<TransacaoRequest> listaFiltro = listTransacoes.stream().filter(t -> t.dataHora().isAfter(horaInicial) || t.dataHora().isEqual(horaInicial)).toList();

        Estatistica estatistica = new Estatistica();

        for (TransacaoRequest request : listaFiltro){
            estatistica.setCount(estatistica.getCount() + 1);
            estatistica.setSum(estatistica.getSum() + request.valor().doubleValue());
            estatistica.setMax(estatistica.getMax() < request.valor().doubleValue() ? request.valor().doubleValue() : estatistica.getMax());

            if (estatistica.getMin() == 0.0){
                estatistica.setMin(request.valor().doubleValue());
            }else{
                estatistica.setMin(estatistica.getMin() < request.valor().doubleValue() ? estatistica.getMin() : request.valor().doubleValue());
            }
        }

        if (estatistica.getSum() > 0 && estatistica.getCount() > 0){
            estatistica.setAvg(estatistica.getSum() / estatistica.getCount().doubleValue());
        }else{
            estatistica.setAvg(0.0);
        }
        return estatistica;
    }
}
