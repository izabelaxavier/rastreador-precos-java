package com.izabela.projetos.rastreadorprecos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // Gera Getters, Setters, toString e Equals/HashCode
@AllArgsConstructor // Gera construtor com todos os campos
@NoArgsConstructor // Gera construtor vazio (importante para o Spring/Hibernate)
public class Produto {
    private String nome;
    private Double preco;
    private String url;
    private LocalDateTime dataConsulta;
}