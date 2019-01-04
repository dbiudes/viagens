package br.com.dorival.viagens.extras;

import java.time.format.DateTimeFormatter;
import java.util.Random;

import br.com.dorival.viagens.dto.Consulta;
import br.com.dorival.viagens.dto.DiariaPreco;
import br.com.dorival.viagens.dto.DiariaQuarto;
import br.com.dorival.viagens.dto.HotelQuarto;

public class DebugUtil {
	
	//TODO: Tornar o parãmetro DEBUG parametrizável
	static Boolean debug = true;
	
	public static void ImprimeParametros(Consulta consulta ) {
		if (debug) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			System.out.println("checkin : " + consulta.getCheckin().format(formatter));
			System.out.println("checkout: " + consulta.getCheckout().format(formatter));
			System.out.println("Adultos : " + consulta.getQuantidadeAdultos());
			System.out.println("Criancas: " + consulta.getQuantidadeCriancas());
			System.out.println("Diarias : " + consulta.getDiarias());	
		}
	}

	public static void ImprimeMensagem(String mensagem) {
		if (debug) {
			System.out.println(mensagem);	
		}
	}
	
	public static void ImprimeCalculo(Consulta consulta, DiariaQuarto diariaQuarto, HotelQuarto hotelQuarto, DiariaPreco detalhePreco, double fatorComComissao) {
		if (debug) {
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
			}
		}
	}
	
}
