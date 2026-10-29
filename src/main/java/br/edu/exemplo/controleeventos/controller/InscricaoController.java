package br.edu.exemplo.controleeventos.controller;

import br.edu.exemplo.controleeventos.model.Evento;
import br.edu.exemplo.controleeventos.model.Inscricao;
import br.edu.exemplo.controleeventos.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InscricaoController {

    private final InscricaoService inscricaoService;
    private final EventoService eventoService;
    private final ParticipanteService participanteService;

    public InscricaoController(InscricaoService inscricaoService, EventoService eventoService, ParticipanteService participanteService) {
        this.inscricaoService = inscricaoService;
        this.eventoService = eventoService;
        this.participanteService = participanteService;
    }

    @GetMapping("/inscricoes")
    public String listar(Model model) {
        model.addAttribute("inscricoes", inscricaoService.listarTodas());
        return "lista-inscricoes";
    }

    @GetMapping("/inscricoes/nova")
    public String nova(Model model) {
        model.addAttribute("inscricao", new Inscricao());
        model.addAttribute("eventos", eventoService.listarTodos());
        model.addAttribute("participantes", participanteService.listarTodos());
        return "form-inscricao";
    }

    @PostMapping("/inscricoes/salvar")
    public String salvar(Inscricao inscricao, Model model) {
        try {
            inscricaoService.salvar(inscricao);
            return "redirect:/inscricoes";
        } catch (IllegalStateException e) {
            model.addAttribute("erro", e.getMessage());
            carregarListas(model);
            return "form-inscricao";
        }
    }

    private void carregarListas(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        model.addAttribute("participantes", participanteService.listarTodos());
    }

    @GetMapping("/inscricoes/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        inscricaoService.excluir(id);
        return "redirect:/inscricoes";
    }

    @GetMapping("/eventos/{id}/inscritos")
    public String inscritosDoEvento(@PathVariable Long id,Model model) {
        Evento evento = eventoService.buscarPorId(id);
        if (evento == null) {
            return "redirect:/eventos";
        }
        var inscricoes = inscricaoService.listarPorEvento(id);
        long total = inscricaoService.contarPorEvento(id);
        model.addAttribute("evento", evento);
        model.addAttribute("inscricoes", inscricoes);
        model.addAttribute("totalInscritos", total);
        return "inscritos-evento";
    }
}
