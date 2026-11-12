package br.edu.exemplo.controleeventos.controller;

import br.edu.exemplo.controleeventos.dto.OcupacaoEvento;
import br.edu.exemplo.controleeventos.service.EventoService;
import br.edu.exemplo.controleeventos.service.InscricaoService;
import br.edu.exemplo.controleeventos.service.ParticipanteService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {

    private final EventoService eventoService;
    private final ParticipanteService participanteService;
    private final InscricaoService inscricaoService;

    public RelatorioController(EventoService eventoService,
            ParticipanteService participanteService,InscricaoService inscricaoService) {
        this.eventoService = eventoService;
        this.participanteService = participanteService;
        this.inscricaoService = inscricaoService;
    }

    @GetMapping("/relatorios")
    public String relatorioGeral(Model model) {
        var eventos = eventoService.listarTodos();
        List<OcupacaoEvento> ocupacoes = eventos.stream()
                .map(evento -> {
                    long inscritos = inscricaoService.contarPorEvento(evento.getId());
                    long disponiveis = evento.getQuantidadeVagas() - inscritos;

                    return new OcupacaoEvento(
                            evento.getTitulo(),
                            evento.getQuantidadeVagas(),
                            inscritos,
                            disponiveis
                    );
                })
                .toList();

        model.addAttribute("totalEventos", eventos.size());
        model.addAttribute("totalParticipantes",
                participanteService.listarTodos().size());
        model.addAttribute("totalInscricoes",
                inscricaoService.listarTodas().size());
        model.addAttribute("ocupacoes", ocupacoes);
        return "relatorio-geral";
    }
}
