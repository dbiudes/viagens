package br.com.dorival.viagens;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.DiariaPreco;
import br.com.dorival.viagens.dto.DiariaQuarto;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.dto.HotelPreco;
import br.com.dorival.viagens.dto.HotelQuarto;
import br.com.dorival.viagens.repository.ConsultarPacotesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,
	properties= {"token.secret=a7ac498c7bba59e0eb7c647d2f0197f8"})
public class ApiViagensApplicationTests {

	@MockBean
	private ConsultarPacotesRepository consultaPacotes;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	final private String pathHotel  = "/pacotespornotel/%s/2017-05-20/2017-05-25/2/3";
	final private String pathCidade = "/pacotesporcidade/%s/2017-05-20/2017-05-25/2/3";

    @Value("${local.server.port}") 
    private int port; // porta atribuida aleatoriamente durante os testes
	
	@Test
	public void contextLoads() {
	}
		
	//TODO: criar testes de validacao de Parametros (invalidos, ausentes)
	
	//TODO: criar testes da api PacotesPorHotel
	
	
	@Test
	public void Consultar_PacotesPorCidade_SemDados() {
		String path = "http://localhost:" + port + String.format(pathCidade, 1032);
		System.out.println("*** testaPacotesPorCidade_SemDados: " + path);
				
		ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(path, Diaria[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isEqualTo(0);		   
	}

	@Test
	public void Consultar_PacotesPorCidade_Mock() {
		String path = "http://localhost:" + port + String.format(pathCidade, 1032); 
		System.out.println("*** testaPacotesPorCidade_Mock: " + path);
		
		given(consultaPacotes.ConsultaVagasPorCidade("1032")).willReturn(ListaDeHoteisMock(5));
		
		ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(path, Diaria[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isEqualTo(5);	//.isGreaterThan(0);		
	}
	
	@Test
	public void validaCalculo_PacotesPorCidade_Mock() {
		String path = "http://localhost:" + port + String.format(pathCidade, 1032); 
		System.out.println("*** testaPacotesPorCidade_Mock: " + path);
		
		given(consultaPacotes.ConsultaVagasPorCidade("1032")).willReturn(ListaDeHoteisMock(5));
		
		ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(path, Diaria[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		Diaria[] diarias = response.getBody();
		assertThat(diarias).isNotNull();
		assertThat(diarias.length).isEqualTo(5);	//.isGreaterThan(0);
		
		Diaria diaria = diarias[0];
		List<DiariaQuarto> diariaQuartos = diaria.getRooms();
		for (DiariaQuarto diariaQuarto : diariaQuartos) {
			if (diariaQuarto.getRoomID() == 1) {
				assertThat(diariaQuarto.getTotalPrice()).isEqualTo("18200.00");				
			}
			if (diariaQuarto.getRoomID() == 2) {
				assertThat(diariaQuarto.getTotalPrice()).isEqualTo("22750.00");
			}
		}
	}
	
	public List<Hotel> ListaDeHoteisMock(int numHoteis) {
		List<Hotel> hoteis = new ArrayList<Hotel>();
		
		Hotel hotel = new Hotel();
		hotel.setCityName("Santo André");
		hotel.setCityCode("30");
		
		List<HotelQuarto> hotelQuartos = new ArrayList<HotelQuarto>();

		//------------------------------------		
		HotelQuarto hQuarto = new HotelQuarto();
		HotelPreco hPreco = new HotelPreco();
		//------------------------------------
		hPreco.setAdult(800.00d);
		hPreco.setChild(400.00d);
		hQuarto.setPrice(hPreco);

		hQuarto.setRoomID(1);
		hQuarto.setCategoryName("TOP LUXO");
		
		hotelQuartos.add(hQuarto);
		//------------------------------------		
		hQuarto = new HotelQuarto();
		hPreco = new HotelPreco();
		
		hPreco.setAdult(1000.00d);
		hPreco.setChild(500.00d);
		hQuarto.setPrice(hPreco);
		hQuarto.setRoomID(2);
		hQuarto.setCategoryName("ULTRA LUXO");
				
		hotelQuartos.add(hQuarto);
		//------------------------------------
		
		hotel.setRooms(hotelQuartos);

		for (int i=0; i<numHoteis; i++) {
			hotel.setId(i+1);
			System.out.println("hotel id: " + hotel.getId());
			hoteis.add(hotel);
		}
		return hoteis;
	}
	
}
