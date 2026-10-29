package br.edu.exemplo.controleeventos.repository;

import br.edu.exemplo.controleeventos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    long countByEventoId(Long eventoId);

    boolean existsByEventoIdAndParticipanteId(
            Long eventoId,
            Long participanteId
    );

    List<Inscricao> findByEventoId(
            Long eventoId
    );
}
