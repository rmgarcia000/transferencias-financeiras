package br.com.transferenciasfinanceirasapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;

public interface AgendamentoTransferenciaFinanceiraService {

	Page<AgendaTransacao> listaTodosAgendamentos(Pageable pageable);

}
