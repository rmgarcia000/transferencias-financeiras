package br.com.transferenciasfinanceirasapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;

import br.com.transferenciasfinanceirasapi.controller.request.AgendarRequest;
import br.com.transferenciasfinanceirasapi.controller.response.AgendarResponse;
import br.com.transferenciasfinanceirasapi.controller.response.BuscaPorIdResponse;
import br.com.transferenciasfinanceirasapi.controller.response.ListaTodosAgendamentosResponse;
import br.com.transferenciasfinanceirasapi.entity.AgendaTransacao;
import br.com.transferenciasfinanceirasapi.service.AgendamentoTransferenciaFinanceiraService;

@RestController("/api/agendamento-transfencia-financeira")
public class AgendamentoTransferenciaFinanceiraController {

	@Autowired
	private AgendamentoTransferenciaFinanceiraService agendaTransacaoService;

	@GetMapping("page/{pagina}/{quantidade}")
	public ResponseEntity<ListaTodosAgendamentosResponse> listaTodosAgendamentos(@PathVariable int pagina, @PathVariable int quantidade) {
		Pageable pageable = PageRequest.of(pagina, quantidade);
		return ResponseEntity.ok(new ListaTodosAgendamentosResponse(agendaTransacaoService.listaTodosAgendamentos(pageable)));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<BuscaPorIdResponse> buscaPorId(@PathVariable("id") int id) {
		return ResponseEntity.ok(new BuscaPorIdResponse(agendaTransacaoService.buscaPorId(id)));
	}

	@PostMapping("/agendar")
	public ResponseEntity<AgendarResponse> agendaTransferenciaFinanceira(@RequestBody AgendarRequest request) {
		return null;
	}

}
