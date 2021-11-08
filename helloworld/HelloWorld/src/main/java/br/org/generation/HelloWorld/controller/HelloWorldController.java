package br.org.generation.HelloWorld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*A aplicação deverá conter um end-point que retorna uma string 
 * com as habilidades e mentalidades que você utilizou para realizar essa atividade.*/

@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

	@GetMapping
	public String helloShow() {
		return ("Habilidades e Mentalidades: \n "
				+ "Persistencia e Atenção aos detalhes, pois elas são fundamentais para aprender um conteúdo novo!!");
	}
}
