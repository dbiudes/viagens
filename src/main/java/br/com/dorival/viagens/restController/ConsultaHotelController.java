package br.com.dorival.viagens.restController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.service.CalcularDiariaService;

@Controller
public class ConsultaHotelController {

	//@GetMapping("/consultarHotel")
	@RequestMapping("/consultarHotel/{id}")
	@ResponseBody
	Diaria consultaHotel(@PathVariable("id") Integer idHotel) {
		
		
		
		System.out.println("Inicio do metodo");
			
		Hotel hotel = new Hotel();
		Diaria diaria = new Diaria();
		
		RestTemplate restTemplate = new RestTemplate();
		hotel = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + idHotel, Hotel.class); 
		if (hotel==null) { throw new IllegalArgumentException("hotel.nao.encontrado"); }
		
		diaria = CalcularDiariaService.ProcessarHotel(hotel, null);
		
		return diaria;
		
		/*
		System.out.println("Cidade pesquisada: " + consulta.getCityCode()); //1032,
		
		List<Diaria> diarias = new ArrayList<Diaria>();
		
		RestTemplate restTemplate = new RestTemplate();
		Hotel[] aHoteis = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + consulta.getCityCode(), Hotel[].class) ; 
		System.out.println("Hoteis encontrados: " + aHoteis.length);
		if (aHoteis==null) { throw new IllegalArgumentException("hoteis.nao.encontrados"); }
		
		List<Hotel> hoteis = Arrays.asList(aHoteis);	
		diarias = hoteis.stream().map(CalcularDiaria::build).collect(Collectors.toList());
		//diarias.add(CalcularDiaria.build(null)); //teste manual
		
		
		//Parametros para calcular a diaria do hotel por periodo e pessoas 
		LocalDate checkin  = consulta.getCheckin();
		LocalDate checkout = consulta.getCheckout();
		Period dias = Period.between(checkin, checkout);
		int qtdAdultos  = consulta.getQuantidadeAdultos(); 
		int qtdCriancas = consulta.getQuantidadeCriancas();
		//onde guardar esses parametros para utilizar em CalcularDiariaQuartos::build ?
		
		
		return (diarias);
		*/
		
	}
	
	@ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		//TODO: gravar log de erro
		response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}


//BrokerHoteis[] hoteis = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + consulta.getCityCode(), Hotel[].class);
//return hoteis;  --> Hoteis[]