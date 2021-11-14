package br.org.generation.farmacia.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	// Atributos
	@Id // Chave Primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
	private long id;
	
	@NotNull(message = "O atributo Nome é obrigatório!")
	@Size(min = 10, max = 255, message = "O Nome da completo deve conter no mínimo 10 caracteres e no máximo 255!")
	private String nome;
	
	@Email(message = "O atributo Usuário deve ser um email válido!")
	@NotNull(message = "O atributo usuário é obrigatório!")
	@Size(max = 255, message = "O usuário deve conter no máximo 255!")
	private String usuario;
	
	@NotBlank(message = "O atributo senha não deve conter espaços!")
	@NotNull(message = "O atributo senha é obrigatório!")
	@Size(min = 8, message = "O usuário deve conter no mínimo 8 caracteres!")
	private String senha;
	
	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O atributo Data de Nascimento é obrigatório!")
	private LocalDate data;
	
	// Getters and Setters
	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getUsuario() { return usuario; }

	public void setUsuario(String usuario) { this.usuario = usuario; }

	public String getSenha() { return senha; }

	public void setSenha(String senha) { this.senha = senha; }

	public LocalDate getData() { return data; }

	public void setData (LocalDate data) { this.data = data; }

}
