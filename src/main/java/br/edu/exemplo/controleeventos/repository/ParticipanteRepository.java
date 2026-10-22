package br.edu.exemplo.controleeventos.repository;

import br.edu.exemplo.controleeventos.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}
