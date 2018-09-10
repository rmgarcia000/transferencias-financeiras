package br.com.transferenciasfinanceirasapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;

public interface AgendaTransacaoRepository extends JpaRepository<AgendaTransacao, Integer> {

}
