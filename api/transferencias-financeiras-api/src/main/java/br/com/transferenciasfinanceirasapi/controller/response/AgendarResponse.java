package br.com.transferenciasfinanceirasapi.controller.response;

import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;

public class AgendarResponse extends Response {

	private AgendaTransacao agendaTransacao;

	public AgendarResponse(AgendaTransacao agendaTransacao) {
		super();
		this.agendaTransacao = agendaTransacao;
	}

	public AgendaTransacao getAgendaTransacao() {
		return agendaTransacao;
	}

	public void setAgendaTransacao(AgendaTransacao agendaTransacao) {
		this.agendaTransacao = agendaTransacao;
	}

}
