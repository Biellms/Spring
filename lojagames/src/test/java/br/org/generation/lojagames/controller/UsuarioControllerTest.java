package br.org.generation.lojagames.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Test
	@Order(1)
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmUsuario() {

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, 
			"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", LocalDate.of(1983, Month.APRIL, 29)));

		ResponseEntity<Usuario> resposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
	}

	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", LocalDate.of(2002, Month.JANUARY, 29)));

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", LocalDate.of(2002, Month.JANUARY, 29)));

		ResponseEntity<Usuario> resposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}

	
	  @Test
	  @Order(3)
	  @DisplayName("Alterar um Usuário") public void deveAtualizarUmUsuario() {
	  
	  Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new
	  Usuario(0L, "Juliana Andrews", "juliana_andrews@email.com.br",
	  "juliana123", LocalDate.of(1983, Month.APRIL, 29)));
	  
	  Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),
	  "Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123", LocalDate.of(2002, Month.JANUARY, 29));
	  
	  HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
	  
	  ResponseEntity<Usuario> resposta = testRestTemplate .withBasicAuth("root",
	  "root") .exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao,
	  Usuario.class);
	  
	  assertEquals(HttpStatus.OK, resposta.getStatusCode());
	  assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
	  assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario()); }
	  
	  @Test
	  @Order(4)
	  @DisplayName("Listar todos os Usuários") public void
	  deveMostrarTodosUsuarios() {
	  
	  usuarioService.cadastrarUsuario(new Usuario(0L, "Sabrina Sanches",
	  "sabrina_sanches@email.com.br", "sabrina123", LocalDate.of(2002, Month.JANUARY, 29)));
	  
	  usuarioService.cadastrarUsuario(new Usuario(0L, "Ricardo Marques",
	  "ricardo_marques@email.com.br", "ricardo123", LocalDate.of(2002, Month.JANUARY, 29)));
	  
	  ResponseEntity<String> resposta = testRestTemplate .withBasicAuth("root",
	  "root") .exchange("/usuarios/all", HttpMethod.GET, null, String.class);
	  
	  assertEquals(HttpStatus.OK, resposta.getStatusCode()); }
	 
}
