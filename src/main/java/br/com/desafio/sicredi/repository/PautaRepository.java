package br.com.desafio.sicredi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafio.sicredi.domain.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
	
    @Query(value = "select p from Pauta p where p.fim <= :date and p.status = :status")
	List<Pauta> findCompletedPautas(LocalDateTime date, String status);
   
    @Query(value = "select p from Pauta p where p.status = :status")
    List<Pauta> findByStatus(String status);
    
    @Query(value = "select p from Pauta p where p.status = :status")
    Page<Pauta> findByStatus(String status, Pageable pageable);
	
	Optional<Pauta> findByNome(String nome);
}