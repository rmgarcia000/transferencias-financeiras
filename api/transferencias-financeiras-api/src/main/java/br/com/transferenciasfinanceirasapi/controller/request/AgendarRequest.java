package br.com.transferenciasfinanceirasapi.controller.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AgendarRequest {
	private String contaOrigem;
	private String contaDestino;
	private BigDecimal valorTransferência;
	private Timestamp dataTransferencia;

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

	public BigDecimal getValorTransferência() {
		return valorTransferência;
	}

	public void setValorTransferência(BigDecimal valorTransferência) {
		this.valorTransferência = valorTransferência;
	}

	public Timestamp getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Timestamp dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

}
