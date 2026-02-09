package com.izabela.projetos.rastreadorprecos.scheduler;

import com.izabela.projetos.rastreadorprecos.model.Produto;
import com.izabela.projetos.rastreadorprecos.service.ScraperService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrecoScheduler {

    private final ScraperService scraperService;

    public PrecoScheduler(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @Scheduled(fixedRate = 10000)
    public void executarMonitoramento() {
        String url = "https://www.amazon.com.br/dp/8576082675";
        System.out.println("⏰ Executando monitoramento automático...");


        Produto p = scraperService.monitorarPreco(url);


        if (p != null) {
            System.out.println("💰 Preço atual capturado: R$ " + p.getPreco());
        }
    }
}
