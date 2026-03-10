package dev.siqueira.desafioitau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class DesafioSpringItauApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioSpringItauApplication.class, args);
    }

}
