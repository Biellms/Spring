package br.org.generation.lojagames.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.lojagames.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	// SELECT * FROM tb_categoria;
	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
}
