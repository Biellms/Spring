package br.org.generation.farmacia.controller;

import java.math.*;
import java.util.*;

import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.org.generation.farmacia.model.Produto;
import br.org.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired // Injeção de dependência
	private ProdutoRepository produtoRepository;
	
	// SELECT * FROM tb_produto;
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	// SELECT * FROM tb_produto WHERE id = ?;
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable long id) {
		//Lambda para verificar se o dado existe
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
		
	// SELECT * FROM tb_produto WHERE nome LIKE '%?%';
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome (@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
		
	// SELECT * FROM tb_produto WHERE marca LIKE '%?%';
		@GetMapping("/marca/{marca}")
		public ResponseEntity<List<Produto>> getByMarca (@PathVariable String marca) {
			return ResponseEntity.ok(produtoRepository.findAllByMarcaContainingIgnoreCase(marca));
	}	
		
	// INSERT INTO tb_produto
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(resp -> {
					return ResponseEntity.ok().body(produtoRepository.save(produto));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	// DELETE * FROM tb_produto WHERE id = ?;
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable long id) {
		//Lambda para verificar se o dado existe
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}).orElse(ResponseEntity.notFound().build());
	}
		
	/* SELECT * FROM tb_produtos 
	WHERE preco > ? ORDER BY preco ASC;*/
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getByMaiorPreco (@PathVariable BigDecimal id) {
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(id));
	}
		
	/* SELECT * FROM tb_produtos 
	WHERE preco < ? ORDER BY preco DESC; */
	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getByMenorPreco (@PathVariable BigDecimal id) {
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(id));
	}
	
	/* SELECT * FROM tb_produto 
	WHERE preco BETWEEN ? AND ?;*/
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produto>> getByPrecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim) {
		return ResponseEntity.ok(produtoRepository.buscarProdutosEntre(inicio, fim));
	}
}
