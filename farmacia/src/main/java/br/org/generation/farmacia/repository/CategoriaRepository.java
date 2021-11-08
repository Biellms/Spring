package br.org.generation.farmacia.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.farmacia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	// SELECT * FROM tb_categoria
	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
}
