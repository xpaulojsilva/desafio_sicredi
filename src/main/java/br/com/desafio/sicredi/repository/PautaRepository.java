package br.com.desafio.sicredi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafio.sicredi.domain.Pauta;
import br.com.desafio.sicredi.enums.StatusVotacao;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
	
    @Query(value = "select p from Pauta p where p.fim <= :date and p.status = :status")
	List<Pauta> findCompletedPautas(LocalDateTime date, StatusVotacao status);
   
    @Query(value = "select p from Pauta p where p.status = :status")
    List<Pauta> findByStatus(StatusVotacao status);
	
	Optional<Pauta> findByNome(String nome);
}