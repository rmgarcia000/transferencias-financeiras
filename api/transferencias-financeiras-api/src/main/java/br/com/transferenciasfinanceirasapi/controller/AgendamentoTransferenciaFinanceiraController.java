package br.com.transferenciasfinanceirasapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.transferenciasfinanceirasapi.controller.request.AgendarRequest;
import br.com.transferenciasfinanceirasapi.controller.request.AgendarResponse;
import br.com.transferenciasfinanceirasapi.controller.request.ListaTodosAgendamentosResponse;
import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;
import br.com.transferenciasfinanceirasapi.service.AgendamentoTransferenciaFinanceiraService;

@RestController("/api/agendamento-transfencia-financeira")
public class AgendamentoTransferenciaFinanceiraController {

	@Autowired
	private AgendamentoTransferenciaFinanceiraService agendaTransacaoService;

	@GetMapping()
	public ResponseEntity<ListaTodosAgendamentosResponse> listaTodosAgendamentos(final Pageable pageable) {
		return ResponseEntity.ok(new ListaTodosAgendamentosResponse(agendaTransacaoService.listaTodosAgendamentos(pageable)));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<AgendaTransacao> buscaPorId(@PathVariable("id") int id) {
		return null;
	}

	@PostMapping("/agendar")
	public ResponseEntity<AgendarResponse> agendaTransferenciaFinanceira(@RequestBody AgendarRequest request) {
		return null;
	}

}
