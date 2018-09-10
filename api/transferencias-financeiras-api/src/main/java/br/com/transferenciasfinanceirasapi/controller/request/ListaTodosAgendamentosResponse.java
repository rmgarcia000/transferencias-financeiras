package br.com.transferenciasfinanceirasapi.controller.request;

import org.springframework.data.domain.Page;

import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;

public class ListaTodosAgendamentosResponse extends Response {

	private Page<AgendaTransacao> listagem;

	public ListaTodosAgendamentosResponse(Page<AgendaTransacao> listagem) {
		super();
		this.listagem = listagem;
	}

	public Page<AgendaTransacao> getListagem() {
		return listagem;
	}

	public void setListagem(Page<AgendaTransacao> listagem) {
		this.listagem = listagem;
	}

}
