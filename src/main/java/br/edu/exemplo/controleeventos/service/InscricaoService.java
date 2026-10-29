package br.edu.exemplo.controleeventos.service;

import br.edu.exemplo.controleeventos.model.*;
import br.edu.exemplo.controleeventos.repository.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository,EventoRepository eventoRepository,ParticipanteRepository participanteRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    public Inscricao salvar(Inscricao inscricao) {
        if (inscricao.getEvento() == null|| inscricao.getEvento().getId() == null) {
            throw new IllegalStateException(
                    "Selecione um evento."
            );
        }
        if (inscricao.getParticipante() == null|| inscricao.getParticipante().getId() == null) {
            throw new IllegalStateException(
                    "Selecione um participante."
            );
        }
        Evento evento = eventoRepository .findById(inscricao.getEvento().getId()).orElseThrow(()
                        -> new IllegalStateException(
                                "Evento não encontrado."
                        )
                );
        Participante participante = participanteRepository.findById(inscricao.getParticipante().getId()).orElseThrow(()
                        -> new IllegalStateException(
                                "Participante não encontrado."
                        )
                );
        if (evento.getStatus() != StatusEvento.ABERTO) {
            throw new IllegalStateException(
                    "Este evento não está aberto para inscrições."
            );
        }
        boolean jaInscrito = inscricaoRepository.existsByEventoIdAndParticipanteId(evento.getId(),participante.getId());
        if (jaInscrito) {
            throw new IllegalStateException(
                    "Este participante já está inscrito neste evento."
            );
        }
        long totalInscritos = inscricaoRepository.countByEventoId(evento.getId());
        if (totalInscritos >= evento.getQuantidadeVagas()) {
            throw new IllegalStateException(
                    "Não há vagas disponíveis para este evento."
            );
        }
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);
        inscricao.setDataInscricao(LocalDateTime.now());
        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarTodas() {
        return inscricaoRepository.findAll();
    }

    public List<Inscricao> listarPorEvento(Long eventoId) {
        return inscricaoRepository.findByEventoId(eventoId);
    }

    public long contarPorEvento(Long eventoId) {
        return inscricaoRepository.countByEventoId(eventoId);
    }

    public void excluir(Long id) {
        inscricaoRepository.deleteById(id);
    }
}
