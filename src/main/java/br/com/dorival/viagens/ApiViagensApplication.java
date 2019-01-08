package br.com.dorival.viagens;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication ( scanBasePackages  = { "br.com.dorival.viagens" })
public class ApiViagensApplication {

	public static void main(String[] args) {
				
		SpringApplication app = new SpringApplication(ApiViagensApplication.class);
	    app.setBannerMode(Mode.CONSOLE);
	    
	    ConfigurableApplicationContext context = app.run(args);
	    	    
	    ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");
	    DateTimeFormatter formatter = DateTimeFormatter
	    		.ofLocalizedDateTime(FormatStyle.SHORT) //	.ofPattern("dd/MM/yyyy HH:mm:ss")
	    		.withLocale(new Locale("pt", "br"));

	    LocalDateTime dataHora = LocalDateTime.ofInstant(Instant.ofEpochMilli(context.getStartupDate()), fusoHorarioDeSaoPaulo);
	    System.out.println("*** Api de Viagens iniciada em " + dataHora.format(formatter) + " ***");
	    
	}
	
}

