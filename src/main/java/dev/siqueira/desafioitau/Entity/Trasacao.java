package dev.siqueira.desafioitau.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trasacao {

    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
