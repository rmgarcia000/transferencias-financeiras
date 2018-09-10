package br.com.transferenciasfinanceirasapi.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.transferenciasfinanceirasapi.controller.request.AgendarRequest;
import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;

public interface AgendamentoTransferenciaFinanceiraService {

	Page<AgendaTransacao> listaTodosAgendamentos(Pageable pageable);

	AgendaTransacao buscaPorId(int id);

	AgendaTransacao agendar(AgendarRequest request, Date dataAgendamento) throws Exception;

}
