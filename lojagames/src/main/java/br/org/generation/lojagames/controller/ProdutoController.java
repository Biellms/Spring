package br.org.generation.lojagames.controller;

import java.math.*;
import java.util.*;

import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.org.generation.lojagames.model.Produto;

import br.org.generation.lojagames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	// SELECT * FROM tb_produto;
	@GetMapping
	public ResponseEntity<List<Produto>> getAll () {
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
	public ResponseEntity<List<Produto>> GetByNome (@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	// SELECT * FROM tb_produto WHERE plataforma LIKE '%?%';
		@GetMapping("/plataforma/{plataforma}")
		public ResponseEntity<List<Produto>> GetByPlataforma (@PathVariable String plataforma) {
			return ResponseEntity.ok(produtoRepository.findAllByPlataformaContainingIgnoreCase(plataforma));
	}
	
	// INSERT INTO tb_produto
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	// UPDATE tb_produto
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
		//Lambda para verificar se o dado existe
		return produtoRepository.findById(produto.getId())
				.map(resposta -> {
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
}
