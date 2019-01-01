package br.com.dorival.viagens.restController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping(method = RequestMethod.GET, value="/consultarPrecos", path = "/consultarPrecos/{cidade}/{checkin}/{checkout}/{adults}/{childrens}")
	@ResponseBody 
	List<Diaria> consultaPorCidade(
			@PathVariable int cidade, 				//CityCode
			@PathVariable LocalDate checkin,		//Checkin Date
			@PathVariable LocalDate checkout,		//Checkout Date
			@PathVariable int adults,				//Quantidade de Adultos
			@PathVariable int childrens) {			//Quantidade de Crianças
		
		CriteriosConsulta consulta = new CriteriosConsulta();
		consulta.setCityCode(cidade);
		consulta.setCheckin(checkin);
		consulta.setCheckout(checkout);
		consulta.setQuantidadeAdultos(adults);
		consulta.setQuantidadeCriancas(childrens);
		consulta.setDiarias(Period.between(consulta.getCheckin(), consulta.getCheckout()).getDays());

		return consultarPrecosService.consultaPrecosPorCidade(consulta);
		
	}
	
	
	@ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		//TODO: gravar log de erro
		response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
