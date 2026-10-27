package br.edu.exemplo.controleeventos.repository;

import br.edu.exemplo.controleeventos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    
}
