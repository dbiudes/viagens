package br.com.dorival.viagens.restController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dorival.viagens.dto.Consulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.service.ConsultarPacotesService;

@RestController
public class ConsultarPacotesController {

	@Autowired 
	ConsultarPacotesService consultarPacotesService;
	
	Consulta consulta = new Consulta();
	
	@RequestMapping(method = RequestMethod.GET, value = "/pacotesporcidade/{idCidade}/{checkin}/{checkout}/{adults}/{childrens}")
	@ResponseBody 
	private List<Diaria> consultarPacotesPorCidade(
			@PathVariable("idCidade")	String idCidade, 									//CityCode
		    @PathVariable("checkin")	@DateTimeFormat(iso=ISO.DATE) LocalDate checkin,	//Checkin  Date yyyy-mm-dd
		    @PathVariable("checkout")	@DateTimeFormat(iso=ISO.DATE) LocalDate checkout,	//Checkout Date	yyyy-mm-dd
			@PathVariable("adults")		String adults,										//Quantidade de Adultos
			@PathVariable("childrens")	String childrens) {									//Quantidade de Crianças
		
		try {
			consulta.setCityCode(idCidade);
		} catch (Exception e) {
			throw new IllegalArgumentException("Codigo cidade nao informado.");
		}
		DefineConsulta(checkin, checkout, adults, childrens);
				
		//para fins de debug
		//System.out.println("Pesquisar Pacotes por Cidade  : " + consulta.getCityCode());
		//ImprimeParametros(consulta);
		
		return consultarPacotesService.consultaPrecosPorCidade(consulta);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pacotesporhotel/{idHotel}/{checkin}/{checkout}/{adults}/{childrens}")
	@ResponseBody 
	private List<Diaria> consultarPacotesPorHotel(
			@PathVariable("idHotel")	String idHotel, 									//idHotel
		    @PathVariable("checkin")	@DateTimeFormat(iso=ISO.DATE) LocalDate checkin,	//Checkin  Date yyyy-mm-dd
		    @PathVariable("checkout")	@DateTimeFormat(iso=ISO.DATE) LocalDate checkout,	//Checkout Date	yyyy-mm-dd
			@PathVariable("adults")		String adults,										//Quantidade de Adultos
			@PathVariable("childrens")	String childrens) {									//Quantidade de Crianças
		
		try {
			consulta.setHotelCode(idHotel);
		} catch (Exception e) {
			throw new IllegalArgumentException("Codigo hotel nao informado.");
		}
		DefineConsulta(checkin, checkout, adults, childrens);
		
		//para fins de debug
		//System.out.println("Pesquisar Pacotes por Hotel  : " + consulta.getHotelCode());
		//ImprimeParametros(consulta); 
		
		return consultarPacotesService.consultaPrecosPorHotel(consulta);
		
	}
	
	private void DefineConsulta(LocalDate checkin, LocalDate checkout, String adults, String childrens) {
		try {
			consulta.setCheckin(checkin);
			consulta.setCheckout(checkout);
			consulta.setQuantidadeAdultos(Integer.parseInt(adults));
			consulta.setQuantidadeCriancas(Integer.parseInt(childrens));
			consulta.setDiarias(Period.between(consulta.getCheckin(), consulta.getCheckout()).getDays());
		} catch (Exception e) {
			throw new IllegalArgumentException("Todos os parametros (checkin, checkout, QtdAdultos e QtdCriancas deve ser informado.");
		}
	}
	
	private void ImprimeParametros(Consulta consulta ) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("checkin : " + consulta.getCheckin().format(formatter));
		System.out.println("checkout: " + consulta.getCheckout().format(formatter));
		System.out.println("Adultos : " + consulta.getQuantidadeAdultos());
		System.out.println("Criancas: " + consulta.getQuantidadeCriancas());
		System.out.println("Diarias : " + consulta.getDiarias());
	}
	
	
	@ExceptionHandler
    private void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		//TODO: gravar log de erro
		response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
