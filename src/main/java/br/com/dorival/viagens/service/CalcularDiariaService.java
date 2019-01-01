package br.com.dorival.viagens.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.dorival.viagens.dto.CriteriosConsulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.DiariaPreco;
import br.com.dorival.viagens.dto.DiariaQuarto;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.dto.HotelQuarto;

public class CalcularDiariaService {
	
	public static Diaria ProcessarHotel(Hotel hotel, CriteriosConsulta consulta) {

		if (hotel == null) {
			System.out.println("HOTEL NAO ESPECIFICADO");
		}
		//---------------------------------------------------------------------------------
		
		Diaria diaria  = new Diaria();
		diaria.setId(hotel.getId());
		diaria.setCityName(hotel.getCityName());

		List<DiariaQuarto> diariaQuartos = new ArrayList<DiariaQuarto>();
		diariaQuartos =  (ArrayList<DiariaQuarto>) hotel.getRooms().stream().map(hotelQuarto -> ProcessarQuarto(hotelQuarto, consulta)).collect(Collectors.toList());
		diaria.setRooms(diariaQuartos);
		
		return diaria;
	}
	

	public static DiariaQuarto ProcessarQuarto(HotelQuarto hotelQuarto, CriteriosConsulta consulta) {
		
		System.out.println("Cidade  : " + consulta.getCityCode());
		System.out.println("checkin : " + consulta.getCheckin());
		System.out.println("checkout: " + consulta.getCheckout());
		System.out.println("Adultos : " + consulta.getQuantidadeAdultos());
		System.out.println("Criancas: " + consulta.getQuantidadeCriancas());


		DiariaQuarto diariaQuarto = new DiariaQuarto();
		DiariaPreco detalhePreco  = new DiariaPreco();

		diariaQuarto.setRoomID(hotelQuarto.getRoomID());
		diariaQuarto.setCategoryName(hotelQuarto.getCategoryName());
		
		diariaQuarto.setTotalPrice(hotelQuarto.getPrice().getAdult() + hotelQuarto.getPrice().getChild());
		
		detalhePreco.setPricePerDayAdult(hotelQuarto.getPrice().getAdult());
		detalhePreco.setPricePerDayChild(hotelQuarto.getPrice().getChild());
		diariaQuarto.setPriceDetail(detalhePreco);
		
		return diariaQuarto;
	}

}
