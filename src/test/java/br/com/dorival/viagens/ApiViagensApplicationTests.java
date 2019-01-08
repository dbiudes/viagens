package br.com.dorival.viagens;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.dto.DiariaQuarto;
import br.com.dorival.viagens.dto.Hotel;
import br.com.dorival.viagens.dto.HotelPreco;
import br.com.dorival.viagens.dto.HotelQuarto;
import br.com.dorival.viagens.repository.ConsultarPacotesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,
	properties= {"token.secret=a7ac498c7bba59e0eb7c647d2f0197f8"})
public class ApiViagensApplicationTests {

	//TODO: criar mais testes de validacao de Parametros (invalidos, ausentes, etc)
	
	@MockBean
	private ConsultarPacotesRepository consultaPacotes;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private HttpEntity<Object> emptyHttpEntity;
	
	final private String pathHotel  = "/pacotesporhotel/{hotelCode}/{checkin}/{checkout}/{adults}/{childrens}"; // /pacotesporhotel/100/2017-05-20/2017-05-25/2/3
	final private String pathCidade = "/pacotesporcidade/{cityCode}/{checkin}/{checkout}/{adults}/{childrens}"; // /pacotesporcidade/1032/2017-05-20/2017-05-25/2/3
	
    @Value("${local.server.port}") 
    private int port; // porta atribuida aleatoriamente durante os testes
	
    
    @PostConstruct
	private void setup() {
		HttpHeaders authHeaders = new HttpHeaders();
		//TODO: implementar token para segurança
		emptyHttpEntity = new HttpEntity<Object>(authHeaders);
	}
    
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void Consultar_PacotesPorHotel_SemMock() {

		System.out.println("*** Consultar_PacotesPorHotel_SemMock: " + pathHotel);
		
		Map<String, Object> urlVariables = new HashMap<>();
		urlVariables.put("hotelCode", 	100);
		urlVariables.put("checkin", 	"2017-05-20");
		urlVariables.put("checkout", 	"2017-05-25");		
		urlVariables.put("adults",  	2);
		urlVariables.put("childrens", 	3);
		
		ResponseEntity<Diaria[]> response = testRestTemplate.exchange(pathHotel, HttpMethod.GET, emptyHttpEntity, Diaria[].class, urlVariables);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isEqualTo(0);
	}
	
	
	@Test
	public void Consultar_PacotesPorHotel_ParametrosInvalidos() {
		
		System.out.println("*** Consultar_PacotesPorHotel_ParametrosInvalidos: " + pathHotel);
		
		Map<String, Object> urlVariables = new HashMap<>();
		urlVariables.put("hotelCode", 	100);
		urlVariables.put("checkin", 	"2017-05-20");
		urlVariables.put("checkout", 	"2017-05-25");		
		urlVariables.put("adults",  	2);
		urlVariables.put("childrens", 	99); //defne numero de crianças acima do permitido

		try {
			ResponseEntity<Diaria[]> response = testRestTemplate.exchange(pathHotel, HttpMethod.GET, emptyHttpEntity, Diaria[].class, urlVariables);
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println("Numero de criancas acima do permitido nao foi aceito - " + e.getMessage());
		}
		
		// ---------------------------------------------------------------
		
		urlVariables = new HashMap<>();
		urlVariables.put("hotelCode", 	100);
		urlVariables.put("checkin", 	"2017-05-20");
		urlVariables.put("checkout", 	"2017-05-25");		
		urlVariables.put("adults",  	2);
		urlVariables.put("childrens", 	""); //informar branco no numero de crianças 

		try {
			ResponseEntity<Diaria[]> response = testRestTemplate.exchange(pathHotel, HttpMethod.GET, emptyHttpEntity, Diaria[].class, urlVariables);
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println("Numero de criancas branco não foi aceitos - " + e.getMessage());
		}
		
	}
	
	
	
	@Test
	public void Consultar_PacotesPorHotel_Mock() {
		
		System.out.println("*** Consultar_PacotesPorHotel_Mock: " + pathHotel);
		
		Map<String, Object> urlVariables = new HashMap<>();
		urlVariables.put("hotelCode", 	100);
		urlVariables.put("checkin", 	"2017-05-20");
		urlVariables.put("checkout", 	"2017-05-25");		
		urlVariables.put("adults",  	2);
		urlVariables.put("childrens", 	3); //defne numero de crianças acima do permitido

		//try {
			given(consultaPacotes.ConsultaVagasPorHotel("1")).willReturn(ListaDeHoteisMock(1));
			
			
			ResponseEntity<Diaria[]> response = testRestTemplate.exchange(pathHotel, HttpMethod.GET, emptyHttpEntity, Diaria[].class, urlVariables);
			//ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(pathHotel, Diaria[].class);
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			//assertThat(response.getBody().length).isEqualTo(1);	//.isGreaterThan(0);
		//} catch (Exception e) {
		//	System.out.println("Numero de criancas acima do permitido nao foi aceito - " + e.getMessage());
		//}
	}
	
	@Test
	public void Consultar_PacotesPorCidade_SemDados() {
	System.out.println("*** Consultar_PacotesPorCidade_SemDados: " + pathHotel);
		
		Map<String, Object> urlVariables = new HashMap<>();
		urlVariables.put("cityCode", 	1032);
		urlVariables.put("checkin", 	"2017-05-20");
		urlVariables.put("checkout", 	"2017-05-25");		
		urlVariables.put("adults",  	2);
		urlVariables.put("childrens", 	3);
	
		ResponseEntity<Diaria[]> response = testRestTemplate.exchange(pathCidade, HttpMethod.GET, emptyHttpEntity, Diaria[].class, urlVariables);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isEqualTo(0);

	}
	
	@Test
	public void validaCalculo_PacotesPorCidade_Mock() {
		
		System.out.println("*** validaCalculo_PacotesPorCidade_Mock: " + pathCidade);
		
		Map<String, Object> urlVariables = new HashMap<>();
		urlVariables.put("cityCode", 	1032);
		urlVariables.put("checkin", 	"2017-05-20");
		urlVariables.put("checkout", 	"2017-05-25");		
		urlVariables.put("adults",  	2);
		urlVariables.put("childrens", 	3);
		
		
		given(consultaPacotes.ConsultaVagasPorCidade("1032")).willReturn(ListaDeHoteisMock(5));
		
		ResponseEntity<Diaria[]> response = testRestTemplate.exchange(pathCidade, HttpMethod.GET, emptyHttpEntity, Diaria[].class, urlVariables);

		//String path = "http://localhost:" + port + String.format(pathCidade, 1032);
		//ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(pathCidade, Diaria[].class);
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
