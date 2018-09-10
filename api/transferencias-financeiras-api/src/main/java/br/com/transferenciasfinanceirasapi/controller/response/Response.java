package br.com.transferenciasfinanceirasapi.controller.response;

public abstract class Response {
	private int codigoRetorno;
	private String mensagem;

	Response() {
		codigoRetorno = 1;
		mensagem = "operacao realizada com sucesso!";
	}
	
	public int getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
