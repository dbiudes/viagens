package br.com.dorival.viagens.extras;

import java.time.format.DateTimeFormatter;

import br.com.dorival.viagens.dto.Consulta;

public class DebugUtil {
	
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
}
