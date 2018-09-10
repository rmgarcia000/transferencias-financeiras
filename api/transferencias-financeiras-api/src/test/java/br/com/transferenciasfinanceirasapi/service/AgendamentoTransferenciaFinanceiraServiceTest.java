package br.com.transferenciasfinanceirasapi.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.transferenciasfinanceirasapi.controller.request.AgendarRequest;
import br.com.transferenciasfinanceirasapi.entity.ContaCorrente;
import br.com.transferenciasfinanceirasapi.repository.ContaCorrenteRepository;
import br.com.transferenciasfinanceirasapi.service.impl.AgendamentoTransferenciaFinanceiraServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AgendamentoTransferenciaFinanceiraServiceTest {

	@InjectMocks
	private AgendamentoTransferenciaFinanceiraService agendamento = new AgendamentoTransferenciaFinanceiraServiceImpl();;

	@Mock
	private ContaCorrenteRepository ctaCorrenteRepository;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy"); 
	
	@Test(expected = Exception.class)
	public void validaDataTransferenciaVazio() throws Exception {
		AgendarRequest request = new AgendarRequest();
		agendamento.agendar(request, new Date());
	}
	
	@Test(expected = Exception.class)
	public void validaDataTransferenciaFormatoInvalido() throws Exception {
		AgendarRequest request = new AgendarRequest();
		request.setDataTransferencia("2018-11-01");
		agendamento.agendar(request, new Date());
	}
	
	@Test(expected = Exception.class)
	public void validaAgendamentoTipoACtaOrigemInvalida() throws Exception {
		Date hoje = new Date();
		AgendarRequest request = new AgendarRequest();
		request.setDataTransferencia(sdf.format(hoje));
		agendamento.agendar(request, hoje);
	}
	
	@Test(expected = Exception.class)
	public void validaAgendamentoTipoACtaDestinoInvalida() throws Exception {
		Mockito.when(ctaCorrenteRepository.findByCta("222222")).thenReturn(new ContaCorrente());

		Date hoje = new Date();
		AgendarRequest request = new AgendarRequest();
		request.setDataTransferencia(sdf.format(hoje));
		request.setContaOrigem("222222");
		
		agendamento.agendar(request, hoje);
	}
	
}
