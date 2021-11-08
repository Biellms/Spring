package br.org.generation.farmacia.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Objeto de domínio, representa uma tabela em um banco de dados relacional
@Table(name = "tb_categoria") // Nome da tabela
public class Categoria {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo tipo é obrigatório!")
	@Size(max = 100, message = "O tipo conter no máximo 100 caracteres!")
	private String tipo;
	
	@NotNull(message = "O atributo tipo é obrigatório!")
	@Size(max = 1000, message = "O tipo conter no máximo 100 caracteres!")
	private String descricao;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) // cascade = Qualquer alteração propaga para todas os relacionamentos.
	@JsonIgnoreProperties("categoria") // Evita o efeito cascata no banco de dados
	private List<Produto> produto;
	
	// Getters and Setters
	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getTipo() { return tipo; }

	public void setTipo(String tipo) { this.tipo = tipo; }

	public String getDescricao() { return descricao; }

	public void setDescricao(String descricao) { this.descricao = descricao; }

	public List<Produto> getProduto() { return produto; }

	public void setProduto(List<Produto> produto) { this.produto = produto; }
	
}
