package br.com.transferenciasfinanceirasapi.controller.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgendarRequest {
	private String contaOrigem;
	private String contaDestino;
	private BigDecimal valorTransferencia;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String dataTransferencia;

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValorTransferencia() {
		return valorTransferencia;
	}

	public void setValorTransferencia(BigDecimal valorTransferência) {
		this.valorTransferencia = valorTransferência;
	}

	public String getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

}
