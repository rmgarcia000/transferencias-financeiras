package br.com.transferenciasfinanceirasapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.transferenciasfinanceirasapi.entity.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {

	ContaCorrente findByCta(String contaOrigem);

}
