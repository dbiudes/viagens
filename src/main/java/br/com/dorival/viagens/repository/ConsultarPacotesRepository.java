package br.com.dorival.viagens.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Repository;

import br.com.dorival.viagens.dto.Hotel;


@Repository
public class ConsultarPacotesRepository {
	
	RestTemplate restTemplate = new RestTemplate();
	
	public List<Hotel> ConsultaVagasPorCidade(String idCidade) {
		Hotel[] aHoteis = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + idCidade, Hotel[].class);
		return Arrays.asList(aHoteis);
	}
	
	public List<Hotel> ConsultaVagasPorHotel(String idHotel) {
		Hotel[] aHoteis = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/" + idHotel, Hotel[].class);
		return Arrays.asList(aHoteis);
	}

}
