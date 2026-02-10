package com.izabela.projetos.rastreadorprecos.repository;

import com.izabela.projetos.rastreadorprecos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}