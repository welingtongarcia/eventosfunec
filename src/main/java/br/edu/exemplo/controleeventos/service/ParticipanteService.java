package br.edu.exemplo.controleeventos.service;

import br.edu.exemplo.controleeventos.model.Participante;
import br.edu.exemplo.controleeventos.repository.ParticipanteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteService {

    private final ParticipanteRepository repository;

    public ParticipanteService(ParticipanteRepository repository) {
        this.repository = repository;
    }

    public Participante salvar(Participante participante) {
        return repository.save(participante);
    }

    public List<Participante> listarTodos() {
        return repository.findAll();
    }

    public Participante buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
