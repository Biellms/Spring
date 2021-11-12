package br.org.generation.lojagames.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.org.generation.lojagames.model.Categoria;
import br.org.generation.lojagames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	// SELECT * FROM tb_categoria;
	@GetMapping
	private ResponseEntity<List<Categoria>> getAll(){	
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	// SELECT * FROM tb_categoria WHERE id = ?;
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable long id) {
		return categoriaRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	// SELECT * FROM tb_categoria WHERE tipo LIKE '%?%';
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Categoria>> getByTipo(@PathVariable String tipo) {
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));	
	}
	
	// INSERT INTO tb_categoria
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	// UPDATE tb_categoria
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) {		
		return categoriaRepository.findById(categoria.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(categoriaRepository.save(categoria));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	// DELETE * FROM tb_categoria WHERE id = ?;
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable long id) {
		//Lambda para verificar se o dado existe
		return categoriaRepository.findById(id)
				.map(resposta -> {
					categoriaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
