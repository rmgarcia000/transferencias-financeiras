package br.com.transferenciasfinanceirasapi.validacoes.impl;

import java.math.BigDecimal;
import java.util.Date;

public class TaxaTipoC extends BaseTaxa<TaxaTipoC> {

	private BigDecimal percetagem = null;

	public TaxaTipoC(Date dataAgendamento, Date dataTranferencia, BigDecimal valorTransacao) {
		super(dataAgendamento, dataTranferencia, valorTransacao);
	}

	@Override
	public BigDecimal calculaTaxa() {
		if (10L < this.getDiaAteTransferencia() && this.getDiaAteTransferencia() <= 20L) {
			this.percetagem = new BigDecimal("0.08").setScale(2);
		}

		if (20L < this.getDiaAteTransferencia() && this.getDiaAteTransferencia() <= 30L) {
			this.percetagem = new BigDecimal("0.06").setScale(2);
		}

		if (30L < this.getDiaAteTransferencia() && this.getDiaAteTransferencia() <= 40L) {
			this.percetagem = new BigDecimal("0.04").setScale(2);
		}

		if (this.getDiaAteTransferencia() > 40L && this.getValorTransacao().compareTo(new BigDecimal("1000000")) == 1) {
			this.percetagem = new BigDecimal("0.02").setScale(2);
		}

		if (this.percetagem == null) {
			return null;
		}

		return this.getValorTransacao().multiply(this.percetagem);		
	}
}
