package br.com.transferenciasfinanceirasapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.transferenciasfinanceirasapi.controller.request.AgendarRequest;
import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;
import br.com.transferenciasfinanceirasapi.entity.ContaCorrente;
import br.com.transferenciasfinanceirasapi.repository.AgendaTransacaoRepository;
import br.com.transferenciasfinanceirasapi.repository.ContaCorrenteRepository;
import br.com.transferenciasfinanceirasapi.service.AgendamentoTransferenciaFinanceiraService;

@Service
public class AgendamentoTransferenciaFinanceiraServiceImpl implements AgendamentoTransferenciaFinanceiraService {

	@Autowired
	private AgendaTransacaoRepository agendaTransacaoRepository;
	
	@Autowired
	private ContaCorrenteRepository ctaCorrenteRepository;

	public Page<AgendaTransacao> listaTodosAgendamentos(Pageable pageable) {
		return agendaTransacaoRepository.findAll(pageable);
	}

	public AgendaTransacao buscaPorId(int id) {
		Optional<AgendaTransacao> agendaTransacao = agendaTransacaoRepository.findById(id);
		if (agendaTransacao.isPresent()) {
			return agendaTransacao.get();
		}
	
		return null;
	}
	
	
	public AgendaTransacao salva(AgendarRequest request) {
		ContaCorrente ctaOrigem = this.ctaCorrenteRepository.findByCta(request.getContaOrigem());
		ContaCorrente ctaDestino = this.ctaCorrenteRepository.findByCta(request.getContaDestino());

		
		return null;
	}
}
