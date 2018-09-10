package br.com.transferenciasfinanceirasapi.validacoes.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.cfg.NotYetImplementedException;

public class TaxaTipoA extends BaseTaxa<TaxaTipoA> {

	private BigDecimal taxaBase = new BigDecimal("3").setScale(2);
	private BigDecimal percetagem = new BigDecimal("0.03").setScale(2);
	
	public TaxaTipoA(Date dataAgendamento, Date dataTranferencia, BigDecimal valorTransacao) {
		super(dataAgendamento, dataTranferencia, valorTransacao);
	}

	@Override
	public BigDecimal calculaTaxa() {
		return super.getValorTransacao().multiply(this.percetagem).add(this.taxaBase);
	}

}
