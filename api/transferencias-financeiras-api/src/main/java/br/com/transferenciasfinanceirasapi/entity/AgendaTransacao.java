package br.com.transferenciasfinanceirasapi.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.transferenciasfinanceirasapi.enums.TipoTransacaoEnum;

@Entity
@Table(name = "tbl_agenda_transacao")
public class AgendaTransacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private TipoTransacaoEnum tipoTransacao;

	private Integer idStatusTransacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cta_origem", referencedColumnName = "id")
	private ContaCorrente ctaOrigem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cta_destino", referencedColumnName = "id")
	private ContaCorrente ctaDestino;

	private BigDecimal vlTaxa;

	private Timestamp dtTransferencia;

	private Timestamp dtAgendamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoTransacaoEnum getIdTipoTransacao() {
		return tipoTransacao;
	}

	public void setIdTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Integer getIdStatusTransacao() {
		return idStatusTransacao;
	}

	public void setIdStatusTransacao(Integer idStatusTransacao) {
		this.idStatusTransacao = idStatusTransacao;
	}

	public TipoTransacaoEnum getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
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

	public Timestamp getDtTransferencia() {
		return dtTransferencia;
	}

	public void setDtTransferencia(Timestamp dtTransferencia) {
		this.dtTransferencia = dtTransferencia;
	}

	public Timestamp getDtAgendamento() {
		return dtAgendamento;
	}

	public void setDtAgendamento(Timestamp dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}
}
