package br.org.generation.farmacia.model;

import java.math.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;

@Entity // Objeto de domínio, representa uma tabela em um banco de dados relacional
@Table(name = "tb_produto") // Nome da Tabela
public class Produto {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo nome é obrigatório!")
	@Size(max = 100, message = "O nome deve conter no máximo 100 caracteres!")
	private String nome;
	
	@NotNull(message = "O atributo descrição é obrigatório!")
	@Size(max = 1000, message = "A descrição deve conter no máximo 1000 caracteres!")
	private String descricao;
	
	@NotNull(message = "O atributo marca é obrigatório!")
	@Size(max = 100, message = "O marca deve conter no máximo 100 caracteres!")
	private String marca;
	
	//@Digits, para limitar o tamanho do decimal
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "O atributo plataforma é obrigatório!")
	@Positive(message = "O preço deve ser maior do que zero!")
	@Digits(integer=6, fraction=2, message = "O preço deve conter 6 inteiros e 2 fracionados!")
	private BigDecimal preco;
	
	private String imagem;
	
	@ManyToOne
	@JsonIgnoreProperties("produto") // Evita o efeito cascata no banco de dados
	private Categoria categoria;

	// Getters and Setters
	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getDescricao() { return descricao; }

	public void setDescricao(String descricao) { this.descricao = descricao; }

	public String getMarca() { return marca; }

	public void setMarca(String marca) { this.marca = marca; }

	public BigDecimal getPreco() { return preco; }

	public void setPreco(BigDecimal preco) { this.preco = preco; }

	public String getImagem() { return imagem; }

	public void setImagem(String imagem) { this.imagem = imagem; }

	public Categoria getCategoria() { return categoria; }

	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	
}
