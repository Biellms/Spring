package br.org.generation.HelloWorld2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*A aplicação deverá conter um end-point que retorna uma string
 * com os seus objetivos de aprendizagem para essa semana.*/

@RestController
@RequestMapping("/helloworld2")
public class HelloWorld2Controller {

	@GetMapping
	public String helloshow2() {
		return ("Meus objetivos de aprendizagem: \n"
				+ "Estudar o Spring-Boot e entender melhor os conceitos e métodos de Back-end!");
	}
}
