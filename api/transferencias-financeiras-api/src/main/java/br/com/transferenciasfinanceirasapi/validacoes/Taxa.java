package br.com.transferenciasfinanceirasapi.validacoes;

import java.math.BigDecimal;

public interface Taxa<T> {

	BigDecimal calculaTaxa();
}