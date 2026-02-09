package com.izabela.projetos.rastreadorprecos.util;

import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
public class LogService {

    // Certifique-se de que o nome é registrarErro (com 'r' minúsculo no começo)
    public void registrarErro(String url, String erro) {
        try (FileWriter fw = new FileWriter("erros_rastreador.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " | URL: " + url + " | ERRO: " + erro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}