package br.com.transferenciasfinanceirasapi.validacoes.impl;

import java.math.BigDecimal;
import java.util.Date;

public class TaxaTipoB extends BaseTaxa<TaxaTipoB> {
	
	private BigDecimal taxaBase = new BigDecimal("12").setScale(2);
	
	private BigDecimal percentual = new BigDecimal(super.getDiaAteTransferencia()).divide(new BigDecimal(100)).setScale(2);
	
	public TaxaTipoB(Date dataAgendamento, Date dataTranferencia, BigDecimal valorTransacao) {
		super(dataAgendamento, dataTranferencia, valorTransacao);
	}

	@Override
	public BigDecimal calculaTaxa() {	
		return this.getValorTransacao().multiply(percentual).add(taxaBase);
	}
}
