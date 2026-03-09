package dev.siqueira.desafioitau.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estatistica {
    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;
}
