package br.com.transferenciasfinanceirasapi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

	public AgendaTransacao agendar(AgendarRequest request) throws Exception {
		ContaCorrente ctaOrigem = this.ctaCorrenteRepository.findByCta(request.getContaOrigem());
		ContaCorrente ctaDestino = this.ctaCorrenteRepository.findByCta(request.getContaDestino());

		AgendaTransacao novoAgendamento = new AgendaTransacao();
		Date dataAgendamento = new Date();
		Date dataTransferencia;
		
		try {
			dataTransferencia = new SimpleDateFormat("dd-MM-yyyy").parse(request.getDataTransferencia());
		} catch (Exception ex) {
			throw new Exception("Necessário informar a data de transferência no padrão dd-MM-yyyy");
		}

		TipoTransacaoEnum tipoTransacao = this.retornaTipoTransacao(dataAgendamento, dataTransferencia);
		BigDecimal vlTaxa = this.calculaTaxa(tipoTransacao, request.getValorTransferência(), dataAgendamento,
				dataTransferencia);

		novoAgendamento.setCtaOrigem(ctaOrigem);
		novoAgendamento.setCtaDestino(ctaDestino);
		novoAgendamento.setVlTransferencia(request.getValorTransferência());
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

	private BigDecimal calculaTaxa(TipoTransacaoEnum tipoTransacao, BigDecimal valorTransferencia, Date dataAgendamento,
			Date dataTransferencia) throws Exception {
		BigDecimal vltaxa;
		long diff = dataAgendamento.getTime() - dataTransferencia.getTime();
		long diasAteTransferencia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		switch (tipoTransacao) {
		case A:
			vltaxa = calculaTaxaA(valorTransferencia);
			break;
		case B:
			vltaxa = calculaTaxaB(diasAteTransferencia, valorTransferencia);
			break;
		case C:
			vltaxa = calculaTaxaC(diasAteTransferencia, valorTransferencia);
			break;
		default:
			vltaxa = null;
		}
		return vltaxa;
	}

	private BigDecimal calculaTaxaA(BigDecimal valorTransferencia) {
		BigDecimal taxaBase = new BigDecimal("0.03").setScale(2);
		BigDecimal percetagem = new BigDecimal("0.03").setScale(2);

		return valorTransferencia.multiply(percetagem).add(taxaBase);
	}

	private BigDecimal calculaTaxaB(long diasAteTransferencia, BigDecimal valorTransferencia) {
		BigDecimal taxaBase = new BigDecimal("12").setScale(2);
		BigDecimal percetagem = new BigDecimal(diasAteTransferencia / 100).setScale(2);

		return valorTransferencia.multiply(percetagem).add(taxaBase);
	}

	private BigDecimal calculaTaxaC(long diasAteTransferencia, BigDecimal valorTransferencia) throws Exception {
		BigDecimal percetagem = null;

		if (10L < diasAteTransferencia && diasAteTransferencia <= 20L) {
			percetagem = new BigDecimal("0.08").setScale(2);
		}

		if (20L < diasAteTransferencia && diasAteTransferencia <= 30L) {
			percetagem = new BigDecimal("0.06").setScale(2);
		}

		if (30L < diasAteTransferencia && diasAteTransferencia <= 40L) {
			percetagem = new BigDecimal("0.04").setScale(2);
		}

		if (diasAteTransferencia > 40L && valorTransferencia.compareTo(new BigDecimal("1000000")) == 1) {
			percetagem = new BigDecimal("0.02").setScale(2);
		}

		if (percetagem == null) {
			return null;
		}

		return valorTransferencia.multiply(percetagem);
	}

	private TipoTransacaoEnum retornaTipoTransacao(Date dataAgendamento, Date dataTransferencia) throws Exception {

		long diff = dataAgendamento.getTime() - dataTransferencia.getTime();
		long diasAteTransferencia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		if (diasAteTransferencia < 0L) {
			throw new Exception("Data de transferencia deve ser maior ou igual do que a data atual");
		}

		if (diasAteTransferencia == 0L) {
			return TipoTransacaoEnum.A;
		}

		if (diasAteTransferencia <= 10) {
			return TipoTransacaoEnum.B;
		}

		return TipoTransacaoEnum.C;
	}
}
