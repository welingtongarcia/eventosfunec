package br.edu.exemplo.controleeventos.controller;

import br.edu.exemplo.controleeventos.model.Evento;
import br.edu.exemplo.controleeventos.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping({"/", "/eventos"})
    public String listarEventos(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos()
        );
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

    @GetMapping("/eventos/editar/{id}")
    public String editarEvento(@PathVariable Long id, Model model) {
        Evento evento = eventoService.buscarPorId(id);
        if (evento == null) {
            return "redirect:/eventos";
        }
        model.addAttribute("evento", evento);
        return "form-evento";
    }

    @GetMapping("/eventos/excluir/{id}")
    public String excluirEvento(@PathVariable Long id) {
        eventoService.excluir(id);
        return "redirect:/eventos";
    }
}
