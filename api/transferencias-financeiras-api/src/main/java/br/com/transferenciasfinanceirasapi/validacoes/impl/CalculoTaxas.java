package br.com.transferenciasfinanceirasapi.validacoes.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.transferenciasfinanceirasapi.enums.TipoTransacaoEnum;
import br.com.transferenciasfinanceirasapi.validacoes.Taxa;

public class CalculoTaxas {

	private Taxa<?> taxa;
	private TipoTransacaoEnum tipoTransacao;

	private Date dataAgendamento;
	private Date dataTranferencia;
	private BigDecimal valorTransacao;
	private BigDecimal vlTaxa;

	public CalculoTaxas(Date dataAgendamento, Date dataTranferencia, BigDecimal valorTransacao) {
		this.dataAgendamento = dataAgendamento;
		this.dataTranferencia = dataTranferencia;
		this.valorTransacao = valorTransacao;
	}

	public BigDecimal calculaTaxa() throws Exception {
		this.geraTipoTransacao().descobreTaxa().executaCalculo();

		return this.vlTaxa;
	}

	public CalculoTaxas executaCalculo() {
		this.vlTaxa = taxa.calculaTaxa();
		return this;
	}

	private CalculoTaxas descobreTaxa() {
		switch (this.tipoTransacao) {
		case A:
			this.taxa = new TaxaTipoA(this.dataAgendamento, this.dataTranferencia, this.valorTransacao);
			break;
		case B:
			this.taxa = new TaxaTipoB(this.dataAgendamento, this.dataTranferencia, this.valorTransacao);
			break;
		case C:
			this.taxa = new TaxaTipoC(this.dataAgendamento, this.dataTranferencia, this.valorTransacao);
			break;
		}

		return this;
	}

	private CalculoTaxas geraTipoTransacao() throws Exception {

		long diff = this.dataTranferencia.getTime() - this.dataAgendamento.getTime();
		long diasAteTransferencia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) +1;

		if (diasAteTransferencia < 0L) {
			throw new Exception("Data de transferencia deve ser maior ou igual do que a data atual");
		}

		if (diasAteTransferencia == 0L) {
			this.tipoTransacao = TipoTransacaoEnum.A;
		} else if (diasAteTransferencia <= 10) {
			this.tipoTransacao = TipoTransacaoEnum.B;
		} else {
			this.tipoTransacao = TipoTransacaoEnum.C;
		}

		return this;
	}

	public TipoTransacaoEnum getTipoTransacao() {
		return this.tipoTransacao;
	}
}
