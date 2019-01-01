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

import br.com.dorival.viagens.dto.CriteriosConsulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.service.ConsultarPrecosService;

@RestController
public class ConsultaPrecosController {

	@Autowired 
	ConsultarPrecosService consultarPrecosService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/consultarPrecos/{cidade}/{checkin}/{checkout}/{adults}/{childrens}")
	@ResponseBody 
	private List<Diaria> consultaPorCidade(
			@PathVariable("cidade")		String cidade, 										//CityCode
		    @PathVariable("checkin")	@DateTimeFormat(iso=ISO.DATE) LocalDate checkin,	//Checkin  Date yyyy-mm-dd
		    @PathVariable("checkout")	@DateTimeFormat(iso=ISO.DATE) LocalDate checkout,	//Checkout Date	yyyy-mm-dd
			@PathVariable("adults")		String adults,										//Quantidade de Adultos
			@PathVariable("childrens")	String childrens) {									//Quantidade de Crianças
		
		CriteriosConsulta consulta = new CriteriosConsulta();
		
		consulta.setCityCode(Integer.parseInt(cidade));
		consulta.setCheckin(checkin);
		consulta.setCheckout(checkout);
		consulta.setQuantidadeAdultos(Integer.parseInt(adults));
		consulta.setQuantidadeCriancas(Integer.parseInt(childrens));
		consulta.setDiarias(Period.between(consulta.getCheckin(), consulta.getCheckout()).getDays());
		
		ImprimeParametros(consulta); //para debug
		
		return consultarPrecosService.consultaPrecosPorCidade(consulta);
		
	}
	
	private void ImprimeParametros(CriteriosConsulta consulta ) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Cidade  : " + consulta.getCityCode());
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
