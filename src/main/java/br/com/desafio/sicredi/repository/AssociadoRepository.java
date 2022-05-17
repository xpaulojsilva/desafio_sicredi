package br.com.desafio.sicredi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.sicredi.domain.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	
	Optional<Associado> findByCpf(String cpf);
}