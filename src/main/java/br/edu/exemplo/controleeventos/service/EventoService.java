package br.edu.exemplo.controleeventos.service;

import br.edu.exemplo.controleeventos.model.Evento;
import br.edu.exemplo.controleeventos.repository.EventoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public Evento salvar(Evento evento) {
        return repository.save(evento);
    }

    public List<Evento> listarTodos() {
        return repository.findAll();
    }
    public Evento buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
