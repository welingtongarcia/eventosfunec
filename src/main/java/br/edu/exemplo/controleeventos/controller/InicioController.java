package br.edu.exemplo.controleeventos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }
}
