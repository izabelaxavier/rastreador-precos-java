package com.izabela.projetos.rastreadorprecos.service;

import com.izabela.projetos.rastreadorprecos.model.Produto;
import com.izabela.projetos.rastreadorprecos.repository.ProdutoRepository;
import com.izabela.projetos.rastreadorprecos.util.LogService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScraperService {

    private final LogService logService;
    private final ProdutoRepository produtoRepository;


    public ScraperService(LogService logService, ProdutoRepository produtoRepository) {
        this.logService = logService;
        this.produtoRepository = produtoRepository;
    }

    public Produto monitorarPreco(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(10000)
                    .get();

            Element tituloElemento = doc.select("h1").first();
            Element autorElemento = doc.select("#bylineInfo, .author").first();
            Element precoElemento = doc.select(".a-price-whole").first();

            if (tituloElemento == null || precoElemento == null) {
                logService.registrarErro(url, "Título ou Preço não encontrados.");
                return null;
            }

            String nome = tituloElemento.text();
            if (autorElemento != null) {
                nome += " - " + autorElemento.text().replace("Seguir o autor", "").trim();
            }

            String precoTexto = precoElemento.text().replaceAll("[^0-9,]", "").replace(",", ".");
            Double preco = Double.parseDouble(precoTexto);

            Produto produto = new Produto(null, nome, preco, url, LocalDateTime.now());

            return produtoRepository.save(produto);

        } catch (Exception e) {
            logService.registrarErro(url, "Falha: " + e.getMessage());
            return null;
        }
    }
}