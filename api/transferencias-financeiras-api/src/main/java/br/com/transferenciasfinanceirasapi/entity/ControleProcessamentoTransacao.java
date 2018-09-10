package br.com.transferenciasfinanceirasapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.transferenciasfinanceirasapi.enums.StatusProcessamentoEnum;
import br.com.transferenciasfinanceirasapi.enums.StatusTransacaoEnum;

@Entity
@Table(name = "tbl_controle_processamento_transacao")
public class ControleProcessamentoTransacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer idAgendaTransacao;
	private String mensagem;
	private StatusProcessamentoEnum statusProcessamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAgendaTransacao() {
		return idAgendaTransacao;
	}

	public void setIdAgendaTransacao(Integer idAgendaTransacao) {
		this.idAgendaTransacao = idAgendaTransacao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public StatusProcessamentoEnum getStatusProcessamento() {
		return statusProcessamento;
	}

	public void setStatusProcessamento(StatusProcessamentoEnum statusProcessamento) {
		this.statusProcessamento = statusProcessamento;
	}
}
