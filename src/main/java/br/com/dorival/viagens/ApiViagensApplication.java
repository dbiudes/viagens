package br.com.dorival.viagens;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication ( scanBasePackages  = { "br.com.dorival.viagens" })
public class ApiViagensApplication {

	public static void main(String[] args) {
	    ConfigurableApplicationContext context = SpringApplication.run(ApiViagensApplication.class, args);
	    	    
	    ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");
	    DateTimeFormatter formatter = DateTimeFormatter
	    		.ofLocalizedDateTime(FormatStyle.SHORT)
	    		.withLocale(new Locale("pt", "br"));
	    	//	.ofPattern("dd/MM/yyyy HH:mm:ss")
	    	    
	    LocalDateTime dataHora = LocalDateTime.ofInstant(Instant.ofEpochMilli(context.getStartupDate()), fusoHorarioDeSaoPaulo);
	    System.out.println("*** ApiViagens iniciada em " + dataHora.format(formatter) + "***"); //08/04/14 24:59:01
	    
	}
	
}

