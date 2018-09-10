package br.com.transferenciasfinanceirasapi.controller.response;

import org.springframework.data.domain.Page;

import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;

public class ListaTodosAgendamentosResponse extends Response {

	private Page<AgendaTransacao> Pageagem;

	public ListaTodosAgendamentosResponse(Page<AgendaTransacao> Pageagem) {
		super();
		this.Pageagem = Pageagem;
	}

	public Page<AgendaTransacao> getPageagem() {
		return Pageagem;
	}

	public void setPageagem(Page<AgendaTransacao> Pageagem) {
		this.Pageagem = Pageagem;
	}

}
