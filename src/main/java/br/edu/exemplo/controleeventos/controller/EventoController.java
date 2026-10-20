package br.edu.exemplo.controleeventos.controller;

import br.edu.exemplo.controleeventos.model.Evento;
import br.edu.exemplo.controleeventos.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping({"/", "/eventos"})
    public String listarEventos(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        return "lista-eventos";
    }

    @GetMapping("/eventos/novo")
    public String novoEvento(Model model) {
        model.addAttribute("evento", new Evento());
        return "form-evento";
    }

    @PostMapping("/eventos/salvar")
    public String salvarEvento(
            @Valid Evento evento,
            BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "form-evento";
        }
        eventoService.salvar(evento);
        return "redirect:/eventos";
    }
}
