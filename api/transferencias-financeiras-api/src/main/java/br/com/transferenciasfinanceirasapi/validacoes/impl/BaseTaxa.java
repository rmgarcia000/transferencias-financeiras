package br.com.transferenciasfinanceirasapi.validacoes.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.hibernate.cfg.NotYetImplementedException;

import br.com.transferenciasfinanceirasapi.validacoes.Taxa;

public abstract class BaseTaxa<T> implements Taxa<T> {
	
	Date dataAgendamento;
	private Date dataTranferencia;
	private BigDecimal valorTransacao;

	protected BaseTaxa(Date dataAgendamento, Date dataTranferencia, BigDecimal valorTransacao) {
		this.dataAgendamento = dataAgendamento;
		this.dataTranferencia = dataTranferencia;
		this.valorTransacao = valorTransacao;
	}

	public BigDecimal getValorTaxa() {
		throw new NotYetImplementedException("Not Yet Implemented");
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = this.trancate(dataAgendamento);
	}

	public Date getDataTranferencia() {
		return dataTranferencia;
	}

	public void setDataTranferencia(Date dataTranferencia) {
		this.dataTranferencia = this.trancate(dataTranferencia);
	}

	public BigDecimal getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(BigDecimal valorTransacao) {
		valorTransacao.setScale(2);
		this.valorTransacao = valorTransacao;
	}

	Date trancate(Date data) {
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public long getDiaAteTransferencia() {
		long diff = this.getDataTranferencia().getTime() - this.getDataAgendamento().getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
	}

}
