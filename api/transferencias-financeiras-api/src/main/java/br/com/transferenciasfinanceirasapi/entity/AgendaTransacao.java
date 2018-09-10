package br.com.transferenciasfinanceirasapi.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.transferenciasfinanceirasapi.enums.StatusTransacaoEnum;
import br.com.transferenciasfinanceirasapi.enums.TipoTransacaoEnum;

@Entity
@Table(name = "tbl_agenda_transacao")
public class AgendaTransacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private TipoTransacaoEnum tipoTransacao;

	@Enumerated(EnumType.STRING)
	private StatusTransacaoEnum statusTransacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cta_origem", referencedColumnName = "id")
	private ContaCorrente ctaOrigem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cta_destino", referencedColumnName = "id")
	private ContaCorrente ctaDestino;

	private BigDecimal vlTaxa;

	private BigDecimal vlTransferencia;

	private Date dtTransferencia;

	private Date dtAgendamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoTransacaoEnum getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public StatusTransacaoEnum getStatusTransacao() {
		return statusTransacao;
	}

	public void setStatusTransacao(StatusTransacaoEnum statusTransacao) {
		this.statusTransacao = statusTransacao;
	}

	public ContaCorrente getCtaOrigem() {
		return ctaOrigem;
	}

	public void setCtaOrigem(ContaCorrente ctaOrigem) {
		this.ctaOrigem = ctaOrigem;
	}

	public ContaCorrente getCtaDestino() {
		return ctaDestino;
	}

	public void setCtaDestino(ContaCorrente ctaDestino) {
		this.ctaDestino = ctaDestino;
	}

	public BigDecimal getVlTaxa() {
		return vlTaxa;
	}

	public void setVlTaxa(BigDecimal vlTaxa) {
		this.vlTaxa = vlTaxa;
	}

	public BigDecimal getVlTransferencia() {
		return vlTransferencia;
	}

	public void setVlTransferencia(BigDecimal vlTransferencia) {
		this.vlTransferencia = vlTransferencia;
	}

	public Date getDtTransferencia() {
		return dtTransferencia;
	}

	public void setDtTransferencia(Date dtTransferencia) {
		this.dtTransferencia = dtTransferencia;
	}

	public Date getDtAgendamento() {
		return dtAgendamento;
	}

	public void setDtAgendamento(Date dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}

}
