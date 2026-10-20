package br.edu.exemplo.controleeventos.repository;

import br.edu.exemplo.controleeventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
