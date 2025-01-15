package medi.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // SE FOSSE UMA APLICAÇÃO NORMAL SERIA APENAS @Controller MAS COMO ESTAMOS TRABALHANDO COM API REST O CERTO É @RestController
@RequestMapping("/hello") // @RequestMapping INDICA QUE É O CAMINHO DA URL QUE VAI CHAMAR ESSE METODO
public class HelloController {

    @GetMapping // CHAMAR O MAPEAMENTO "/hello"
    public String olaMundo(){
        return "Hello world Spring!";
    }

}
