package br.com.transferenciasfinanceirasapi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.transferenciasfinanceirasapi.controller.request.AgendarRequest;
import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;
import br.com.transferenciasfinanceirasapi.entity.ContaCorrente;
import br.com.transferenciasfinanceirasapi.enums.StatusTransacaoEnum;
import br.com.transferenciasfinanceirasapi.enums.TipoTransacaoEnum;
import br.com.transferenciasfinanceirasapi.repository.AgendaTransacaoRepository;
import br.com.transferenciasfinanceirasapi.repository.ContaCorrenteRepository;
import br.com.transferenciasfinanceirasapi.service.AgendamentoTransferenciaFinanceiraService;
import br.com.transferenciasfinanceirasapi.util.DateUtil;
import br.com.transferenciasfinanceirasapi.validacoes.impl.CalculoTaxas;

@Service
public class AgendamentoTransferenciaFinanceiraServiceImpl implements AgendamentoTransferenciaFinanceiraService {

	@Autowired
	private AgendaTransacaoRepository agendaTransacaoRepository;

	@Autowired
	private ContaCorrenteRepository ctaCorrenteRepository;

	public Page<AgendaTransacao> listaTodosAgendamentos(Pageable pageable) {
		return this.agendaTransacaoRepository.findAll(pageable);
	}

	public AgendaTransacao buscaPorId(int id) {
		Optional<AgendaTransacao> agendaTransacao = this.agendaTransacaoRepository.findById(id);
		if (agendaTransacao.isPresent()) {
			return agendaTransacao.get();
		}

		return null;
	}

	public AgendaTransacao agendar(AgendarRequest request, Date dataAgendamento) throws Exception {
		ContaCorrente ctaOrigem = this.ctaCorrenteRepository.findByCta(request.getContaOrigem());
		ContaCorrente ctaDestino = this.ctaCorrenteRepository.findByCta(request.getContaDestino());

		AgendaTransacao novoAgendamento = new AgendaTransacao();
		Date dataTransferencia;
		
		try {
			dataTransferencia = new SimpleDateFormat("dd-MM-yyyy").parse(request.getDataTransferencia());
		} catch (Exception ex) {
			throw new Exception("Necessário informar a data de transferência no padrão dd-MM-yyyy");
		}

		CalculoTaxas calculoTaxas = new CalculoTaxas(dataAgendamento, dataTransferencia, request.getValorTransferencia());
		BigDecimal vlTaxa = calculoTaxas.calculaTaxa();
		TipoTransacaoEnum tipoTransacao = calculoTaxas.getTipoTransacao();

		novoAgendamento.setCtaOrigem(ctaOrigem);
		novoAgendamento.setCtaDestino(ctaDestino);
		novoAgendamento.setVlTransferencia(request.getValorTransferencia());
		novoAgendamento.setVlTaxa(vlTaxa);
		novoAgendamento.setDtTransferencia(dataTransferencia);
		novoAgendamento.setDtAgendamento(dataAgendamento);
		novoAgendamento.setTipoTransacao(tipoTransacao);
		novoAgendamento.setStatusTransacao(StatusTransacaoEnum.AT);

		validaNovoAgendamento(novoAgendamento);

		return agendaTransacaoRepository.save(novoAgendamento);
	}

	
	private void validaNovoAgendamento(AgendaTransacao novoAgendamento) throws Exception {
		if (novoAgendamento.getCtaDestino() == null) {
			throw new Exception("Não foi possível encontrar a conta de destino");
		}

		if (novoAgendamento.getCtaOrigem() == null) {
			throw new Exception("Não foi possível encontrar a conta de origem");
		}

		if (novoAgendamento.getDtAgendamento() == null) {
			throw new Exception("Data de egendamento obrigatória");
		}

		if (novoAgendamento.getDtTransferencia() == null) {
			throw new Exception("Data de transferência obrigatória");
		}

		if (DateUtil.comparaData(novoAgendamento.getDtTransferencia(), new Date()) > 0) {
			throw new Exception("Data de transferência deve ser maior ou igual a data atual");
		}

		if (novoAgendamento.getStatusTransacao() == null) {
			throw new Exception("Status da transação obrigatório");
		}

		if (novoAgendamento.getTipoTransacao() == null) {
			throw new Exception("Tipo da transação obrigatório");
		}

		if (novoAgendamento.getVlTaxa() == null) {
			throw new Exception("Valor da taxa obrigatório");
		}

		if (novoAgendamento.getVlTransferencia() == null || novoAgendamento.getVlTransferencia() == BigDecimal.ZERO) {
			throw new Exception("Valor da transferência obrigatório");
		}
	}
}
