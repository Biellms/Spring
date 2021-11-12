package br.org.generation.lojagames.model;

import java.math.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "tb_produtos") // Nome da tabela
public class Produto {

	// Atributos
	@Id // Chave Primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo nome é obrigatório!")
	@Size(max = 100, message = "O nome deve conter no mínimo 5 caracteres e no máximo 100!")
	private String nome;
	
	@NotNull(message = "O atributo descricao é obrigatório!")
	@Size(max = 1000, message = "A descricao deve conter no mínimo 10 caracteres e no máximo 1000!")
	private String descricao;

	@NotNull(message = "O atributo plataforma é obrigatório!")
	@Size(max = 100, message = "A plataforma deve conter no mínimo 5 caracteres e no máximo 100!")
	private String plataforma;
	
	//@Digits, para limitar o tamanho do decimal
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "O atributo plataforma é obrigatório!")
	@Positive(message = "O preço deve ser maior do que zero!")
	@Digits(integer=6, fraction=2, message = "O decimal deve conter 6 inteiros e 2 fracionados!")
	private BigDecimal preco;
	
	private String imagem;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	// Getters and Setters
	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getDescricao() { return descricao; }

	public void setDescricao(String descricao) { this.descricao = descricao; }

	public String getPlataforma() { return plataforma; }

	public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

	public BigDecimal getPreco() { return preco; }

	public void setPreco(BigDecimal preco) { this.preco = preco; }

	public String getImagem() { return imagem; }

	public void setImagem(String imagem) { this.imagem = imagem; }

	public Categoria getCategoria() { return categoria; }

	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	
}