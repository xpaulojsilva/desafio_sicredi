package br.com.desafio.sicredi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.sicredi.domain.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
	
	Optional<Voto> findByAssociadoIdAndPautaId(long associado, long pauta);

	List<Voto> findByPautaId(long i);
}