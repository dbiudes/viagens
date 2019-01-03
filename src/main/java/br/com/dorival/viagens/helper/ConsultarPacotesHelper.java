package br.com.dorival.viagens.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import br.com.dorival.viagens.dto.Consulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.DiariaPreco;
import br.com.dorival.viagens.dto.DiariaQuarto;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.dto.HotelQuarto;



public class ConsultarPacotesHelper {
	
	public static Diaria ProcessarHotel(Hotel hotel, Consulta consulta) {

		Diaria diaria  = new Diaria();
		diaria.setId(hotel.getId());
		diaria.setName(hotel.getName());
		diaria.setCityCode(hotel.getCityCode());
		diaria.setCityName(hotel.getCityName());

		List<DiariaQuarto> diariaQuartos = new ArrayList<DiariaQuarto>();
		diariaQuartos =  (ArrayList<DiariaQuarto>) hotel.getRooms().stream().map(hotelQuarto -> ProcessarQuarto(hotelQuarto, consulta)).collect(Collectors.toList());
		diaria.setRooms(diariaQuartos);
		
		return diaria;
	}
	

	public static DiariaQuarto ProcessarQuarto(HotelQuarto hotelQuarto, Consulta consulta) {
		
		//TODO: Tornar a comissao parametrizavel
		double fatorComComissao = 1.3d; 	     //caso a consulta seja por hotem  (sem diarias) o fator sera uma diaria + 30% comissao
		if (consulta.getDiarias() > 0 ) {
			fatorComComissao = (consulta.getDiarias() * 1.3);	//a formula ({total}/0.7) nao representa os 30% de comissao estipulados
		}
		
		double PricePerDayAdult = hotelQuarto.getPrice().getAdult() * consulta.getQuantidadeAdultos()  * fatorComComissao;
		double PricePerDayChild = hotelQuarto.getPrice().getChild() * consulta.getQuantidadeCriancas() * fatorComComissao;
		
		NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
		((DecimalFormat)df).applyPattern("0.00");
		
		DiariaPreco detalhePreco  = new DiariaPreco();
		detalhePreco.setPricePerDayAdult(df.format(PricePerDayAdult));
		detalhePreco.setPricePerDayChild(df.format(PricePerDayChild));
		
		DiariaQuarto diariaQuarto = new DiariaQuarto();
		diariaQuarto.setPriceDetail(detalhePreco);
		diariaQuarto.setRoomID(hotelQuarto.getRoomID());
		diariaQuarto.setCategoryName(hotelQuarto.getCategoryName());
		diariaQuarto.setTotalPrice(df.format(PricePerDayAdult + PricePerDayChild));

		//para fins de debug (validação dos calculos)
		/* 
		Random random = new Random(); 
		int x = random.nextInt(1000);
		if (x == 10) {
			System.out.println("------------------------------------------------");
			System.out.println("Diarias: " + consulta.getDiarias() + " Adultos : " + consulta.getQuantidadeAdultos());
			System.out.println("Adulto Hotel: " + hotelQuarto.getPrice().getAdult());
			System.out.println("Diarias>Adulto+Comissao  : " + detalhePreco.getPricePerDayAdult());
			System.out.println("------------------------------------------------");
			System.out.println("Diarias: " + consulta.getDiarias() + " Criancas: " + consulta.getQuantidadeCriancas());
			System.out.println("Crianca Hotel: " + hotelQuarto.getPrice().getChild());
			System.out.println("Diarias>Crianca+Comissao  : " + detalhePreco.getPricePerDayChild());
			System.out.println("------------------------------------------------");
			System.out.println("Fator Comissao: " + fatorComComissao);
			System.out.println("TOTAL PRICE: " + diariaQuarto.getTotalPrice());
		} 	*/
		
		return diariaQuarto;
	}
	
	public static Consulta DefineValidaConsulta(String checkin, String checkout, String adults, String childrens) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Consulta consulta = new Consulta();
				
		try {
			consulta.setCheckin(LocalDate.parse(checkin, formatter));
		} catch (Exception e) {
			throw new IllegalArgumentException("Data de Checkin informada incorretamente.");
		}
		try {
			consulta.setCheckout(LocalDate.parse(checkout, formatter));
		} catch (Exception e) {
			throw new IllegalArgumentException("Data de Checkout informada incorretamente.");
		}
		try {
			consulta.setQuantidadeAdultos(Integer.parseInt(adults));
		} catch (Exception e) {
			throw new IllegalArgumentException("Número de Adultos informado incorretamente.");
		}
		try {
			consulta.setQuantidadeCriancas(Integer.parseInt(childrens));
		} catch (Exception e) {
			throw new IllegalArgumentException("Número de Criancas informado incorretamente.");
		}
		
		
		try {
			consulta.setDiarias(Period.between(consulta.getCheckin(), consulta.getCheckout()).getDays());
		} catch (Exception e) {
			throw new IllegalArgumentException("Datas de checkin e checkout informadas incorretamente.");
		}
		if (consulta.getDiarias() <= 0) {
			throw new IllegalArgumentException("Datas de checkin e checkout informadas incorretamente.");
		}
		
		if (consulta.getQuantidadeAdultos() < 1) {
			throw new IllegalArgumentException("Pacote deve possuir ao menos um adulto.");
		}
		if (consulta.getQuantidadeAdultos() > 9) {
			throw new IllegalArgumentException("Número de adultos não deve ser superior a 9.");
		}
		
		if (consulta.getQuantidadeCriancas() < 0) {
			throw new IllegalArgumentException("Número de crianças informado indevidamente.");
		}
		if (consulta.getQuantidadeCriancas() >9) {
			throw new IllegalArgumentException("Número de crianças não deve ser superior a 9.");
		}
		return consulta;
	}

	
	
}
