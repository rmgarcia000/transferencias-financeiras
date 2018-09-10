package br.com.transferenciasfinanceirasapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;
import br.com.transferenciasfinanceirasapi.repository.AgendaTransacaoRepository;
import br.com.transferenciasfinanceirasapi.service.AgendamentoTransferenciaFinanceiraService;

public class AgendamentoTransferenciaFinanceiraServiceImpl implements AgendamentoTransferenciaFinanceiraService {

	@Autowired
	private AgendaTransacaoRepository agendaTransacaoRepository;
	
	public Page<AgendaTransacao> listaTodosAgendamentos(Pageable pageable) {
		return agendaTransacaoRepository.findAll(pageable);
	}
}
