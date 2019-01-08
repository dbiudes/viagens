package br.com.dorival.viagens.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.dorival.viagens.dto.ConsultaCidade;
import br.com.dorival.viagens.dto.ConsultaHotel;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.extras.DebugUtil;
import br.com.dorival.viagens.service.ConsultarPacotesService;
import br.com.dorival.viagens.util.ConsultarPacotesHelper;

@RestController
public class ConsultarPacotesController implements ConsultarPacotesAPI {

	@Autowired 
	ConsultarPacotesService consultarPacotesService;
	
	
	public List<Diaria> consultarPacotesPorCidade(
			@PathVariable("ID da Cidade")		String idCidade,
			@PathVariable("Data de Checkin")	String checkin,
			@PathVariable("Data de Checkout")	String checkout,
			@PathVariable("Numero de Adultos")	String adults,
			@PathVariable("Numero de Criancas")	String childrens) {
		
		ConsultaCidade consultaCidade = new ConsultaCidade ();
		
		consultaCidade.setConsulta(ConsultarPacotesHelper.DefineValidaConsulta(checkin, checkout, adults, childrens));  
						
		try {
			consultaCidade.setCityCode(idCidade);
		} catch (Exception e) {
			throw new IllegalArgumentException("Código da cidade não informado.");
		}
		DebugUtil.ImprimeMensagem("Pesquisar pacotes e preços na cidade " + consultaCidade.getCityCode());
		DebugUtil.ImprimeParametros(consultaCidade.getConsulta());
		
		return consultarPacotesService.consultaPrecosPorCidade(consultaCidade);
		
	}

	@Override
	public List<Diaria> consultarPacotesPorHotel(
		    @PathVariable("ID do Hotel") 		String idHotel,
			@PathVariable("Data de Checkin")	String checkin,
			@PathVariable("Data de Checkout")	String checkout,
			@PathVariable("Numero de Adultos")	String adults,
			@PathVariable("Numero de Criancas")	String childrens) {

						
		ConsultaHotel consultaHotel = new ConsultaHotel ();
		
		consultaHotel.setConsulta(ConsultarPacotesHelper.DefineValidaConsulta(checkin, checkout, adults, childrens));  
		
		try {
			consultaHotel.setHotelCode(idHotel);
		} catch (Exception e) {
			throw new IllegalArgumentException("Código do hotel não informado.");
		}
		DebugUtil.ImprimeMensagem("Consultar quartos e preços no hotel  : " + consultaHotel.getHotelCode());
		DebugUtil.ImprimeParametros(consultaHotel.getConsulta()); 
		
		return consultarPacotesService.consultaPrecosPorHotel(consultaHotel);
		
	}

}
