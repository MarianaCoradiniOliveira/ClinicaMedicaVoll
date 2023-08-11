package med.voll.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Rest pois estamos fazendo uma API REST
@RequestMapping("/hello") //qual URL que este controller vai responder, fazer o mapeamento
public class helloController {

    @GetMapping //de qual método do protocol HTTP é pra chamar este método
    public String olaMundo(){
        return "Ola mariana";
    }
}
