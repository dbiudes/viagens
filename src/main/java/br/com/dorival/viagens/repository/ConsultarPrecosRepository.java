package br.com.dorival.viagens.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.dto.HotelPreco;
import br.com.dorival.viagens.dto.HotelQuarto;

@Repository
public class ConsultarPrecosRepository {

	public List<Hotel> PesquisaHoteis(int idCidade) {
		// 1032 - Porto Seguro, 7110 - Rio de Janeiro, 9626 - São Paulo
		RestTemplate restTemplate = new RestTemplate();
		Hotel[] aHoteis = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + idCidade, Hotel[].class) ; 
		if (aHoteis!=null && aHoteis.length > 0) {
			System.out.println("Cidade pesquisada: " + idCidade + " - Hoteis encontrados: " + aHoteis.length);
		} else {
			System.out.println("Não foram encontrados hoteis para a cidade : " + idCidade);
			throw new IllegalArgumentException("hoteis.nao.encontrados"); 
		}
		return Arrays.asList(aHoteis);
	}
	
	
	public List<Hotel> criaHotelTeste() {
		
		List<Hotel> hoteis = new ArrayList<Hotel>();
		
		
		Hotel hotel = new Hotel();
		hotel.setId(222);
		hotel.setCityName("Mauá");
		List<HotelQuarto> hotelQuartos = new ArrayList<HotelQuarto>();

		//------------------------------------		
		HotelQuarto hQuarto = new HotelQuarto();
		HotelPreco hPreco = new HotelPreco();
		//------------------------------------
		hPreco.setAdult(99.00d);
		hPreco.setChild(44.00d);
		hQuarto.setPrice(hPreco);

		hQuarto.setRoomID(1);
		hQuarto.setCategoryName("TOP LUXO");
		System.out.println("Cadastro de categoria: " + hQuarto.getCategoryName());
		
		hotelQuartos.add(hQuarto);
		//------------------------------------		
		hQuarto = new HotelQuarto();
		hPreco = new HotelPreco();
		
		hPreco.setAdult(125.00d);
		hPreco.setChild(77.00d);
		hQuarto.setPrice(hPreco);
		hQuarto.setRoomID(2);
		hQuarto.setCategoryName("ULTRA LUXO");
		System.out.println("Cadastro de categoria: " + hQuarto.getCategoryName());
				
		hotelQuartos.add(hQuarto);
		//------------------------------------
		
		hotel.setRooms(hotelQuartos);
		
		hotelQuartos = null;
		hQuarto = null;
		hPreco = null;
		
		hoteis.add(hotel);
		hoteis.add(hotel);
		
		return hoteis;
	}
}
