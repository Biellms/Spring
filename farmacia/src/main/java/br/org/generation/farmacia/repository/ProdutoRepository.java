package br.org.generation.farmacia.repository;

import java.util.*;
import java.math.*;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.farmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	// SELECT * FROM tb_categoria;
		public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
		public List<Produto> findAllByMarcaContainingIgnoreCase(String marca);
		
		// SELECT * FROM tb_produto WHERE preco > ? ORDER BY preco;
		public List <Produto> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);
		
		// SELECT * FROM tb_produto WHERE preco < ? ORDER BY preco DESC;
		public List <Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
}
