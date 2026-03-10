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
    private Long count = 0L;
    private Double sum = 0D;
    private Double avg = 0D;
    private Double min = 0D;
    private Double max = 0D;
}
