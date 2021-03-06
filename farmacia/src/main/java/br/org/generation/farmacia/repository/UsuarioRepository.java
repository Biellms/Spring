package br.org.generation.farmacia.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.farmacia.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// SELECT * FROM tb_usuario WHERE usuario LIKE '%?%';
	public Optional<Usuario> findByUsuario(String usuario);
}
