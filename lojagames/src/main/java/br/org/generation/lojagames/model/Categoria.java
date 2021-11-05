package br.org.generation.lojagames.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria") // Nome da tabela
public class Categoria {

	// Atributos
	@Id // Chave Primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo tipo é obrigatório!")
	@Size(min = 5, max = 100, message = "O tipo deve conter no mínimo 5 caracteres e no máximo 100!")
	private String tipo;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;
	
	// Getters and Setters
	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getTipo() {return tipo; }

	public void setTipo(String tipo) { this.tipo = tipo; }

	public List<Produto> getProduto() { return produto; }

	public void setProduto(List<Produto> produto) { this.produto = produto; }

}
