package dev.siqueira.desafioitau.service;

import dev.siqueira.desafioitau.Entity.Estatistica;
import dev.siqueira.desafioitau.config.EstatisticaProperties;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {

    private final TrasacaoRepository trasacaoRepository;
    private final EstatisticaProperties estatisticaProperties;

    public EstatisticaService(TrasacaoRepository trasacaoRepository, EstatisticaProperties estatisticaProperties) {
        this.trasacaoRepository = trasacaoRepository;
        this.estatisticaProperties = estatisticaProperties;
    }

    public Estatistica gerarEstatistica(List<TransacaoRequest> listTransacoes){
        OffsetDateTime horaInicial = OffsetDateTime.now().minusDays(estatisticaProperties.segundos());

        if (listTransacoes.isEmpty()){
            return new Estatistica(0L,0.0D,0.0D,0.0D,0.0D);
        }

        DoubleSummaryStatistics resumo = listTransacoes.stream()
                .filter(t -> t.dataHora().isAfter(horaInicial) || t.dataHora().isEqual(horaInicial))
                .mapToDouble(t -> t.valor().doubleValue())
                .summaryStatistics();

        return new Estatistica(resumo.getCount(), resumo.getSum(), resumo.getAverage(), resumo.getMin(), resumo.getMax());
    }
}
