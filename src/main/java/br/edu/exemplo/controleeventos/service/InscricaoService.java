package br.edu.exemplo.controleeventos.service;

import br.edu.exemplo.controleeventos.model.Inscricao;
import br.edu.exemplo.controleeventos.repository.InscricaoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    private final InscricaoRepository repository;

    public InscricaoService(InscricaoRepository repository) {
        this.repository = repository;
    }

    public Inscricao salvar(Inscricao inscricao) {
        if (inscricao.getDataInscricao() == null) {
            inscricao.setDataInscricao(
                    LocalDateTime.now()
            );
        }
        if (inscricao.getStatus() == null || inscricao.getStatus().isBlank()) {
            inscricao.setStatus("ATIVA");
        }
        return repository.save(inscricao);
    }

    public List<Inscricao> listarTodas() {
        return repository.findAll();
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
