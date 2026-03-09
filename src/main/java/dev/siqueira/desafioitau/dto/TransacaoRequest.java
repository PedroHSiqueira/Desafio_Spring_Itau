package dev.siqueira.desafioitau.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransacaoRequest(BigDecimal valor, OffsetDateTime dataHora) {
}
