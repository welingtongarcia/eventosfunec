package br.edu.exemplo.controleeventos.controller;

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

    public InscricaoController(InscricaoService inscricaoService,EventoService eventoService,ParticipanteService participanteService) {
        this.inscricaoService = inscricaoService;
        this.eventoService = eventoService;
        this.participanteService = participanteService;
    }

    @GetMapping("/inscricoes")
    public String listar(Model model) {
        model.addAttribute("inscricoes",inscricaoService.listarTodas());
        return "lista-inscricoes";
    }

    @GetMapping("/inscricoes/nova")
    public String nova(Model model) {
        model.addAttribute("inscricao",new Inscricao());
        model.addAttribute("eventos",eventoService.listarTodos());
        model.addAttribute("participantes",participanteService.listarTodos());
        return "form-inscricao";
    }

    @PostMapping("/inscricoes/salvar")
    public String salvar(Inscricao inscricao) {
        inscricaoService.salvar(inscricao);
        return "redirect:/inscricoes";
    }

    @GetMapping("/inscricoes/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        inscricaoService.excluir(id);
        return "redirect:/inscricoes";
    }
}
