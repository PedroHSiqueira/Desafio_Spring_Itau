package dev.siqueira.desafioitau.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "estatistica")
public record EstatisticaProperties(Integer segundos) {
}
