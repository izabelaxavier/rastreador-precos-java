package com.izabela.projetos.rastreadorprecos;

import com.izabela.projetos.rastreadorprecos.model.Produto;
import com.izabela.projetos.rastreadorprecos.service.ScraperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RastreadorPrecosApplication {

    public static void main(String[] args) {
        SpringApplication.run(RastreadorPrecosApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ScraperService scraperService) {
        return args -> {

            String url = "https://www.amazon.com.br/dp/8576082675";

            System.out.println("--- Iniciando Teste de Conexão (Amazon) ---");

            Produto p = scraperService.monitorarPreco(url);

            if (p != null) {
                System.out.println("✅ Sucesso! Produto: " + p.getNome());
                System.out.println("✅ Preço capturado: R$ " + p.getPreco());
            } else {
                System.out.println("❌ Falha no teste inicial. Verifique o erros_rastreador.txt");
            }
        };
    }
}