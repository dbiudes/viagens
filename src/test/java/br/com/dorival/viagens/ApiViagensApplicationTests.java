package br.com.dorival.viagens;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
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
	public void testaPacotesPorCidade_SemDados() {
		String path = "http://localhost:" + port + String.format(pathCidade, 1032);
		System.out.println("*** testaPacotesPorCidade_SemDados: " + path);
				
		ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(path, Diaria[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isEqualTo(0);		   
	}

	@Test
	public void testaPacotesPorCidade_Mock() {
		String path = "http://localhost:" + port + String.format(pathCidade, 1032); 
		System.out.println("*** testaPacotesPorCidade_Mock: " + path);
		
		given(consultaPacotes.ConsultaVagasPorCidade("1032")).willReturn(ListaDeHoteisMock(5));
		
		ResponseEntity<Diaria[]> response = testRestTemplate.getForEntity(path, Diaria[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isEqualTo(5);	//.isGreaterThan(0);
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

/*

@Test
public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
  throws ClientProtocolException, IOException {
  
   // Given
   String name = RandomStringUtils.randomAlphabetic( 8 );
   HttpUriRequest request = new HttpGet( "https://api.github.com/users/" + name );
 
   // When
   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
 
   // Then
   assertThat(
     httpResponse.getStatusLine().getStatusCode(),
     equalTo(HttpStatus.SC_NOT_FOUND));
}

@Test
public void
givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
  throws ClientProtocolException, IOException {
  
   // Given
   String jsonMimeType = "application/json";
   HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );
 
   // When
   HttpResponse response = HttpClientBuilder.create().build().execute( request );
 
   // Then
   String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
   assertEquals( jsonMimeType, mimeType );
}

@Test
public void
  givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect()
  throws ClientProtocolException, IOException {
  
    // Given
    HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );
 
    // When
    HttpResponse response = HttpClientBuilder.create().build().execute( request );
 
    // Then
    GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(
    		response, GitHubUser.class);
    
    assertThat( "eugenp", Matchers.is( resource.getLogin() ) );
}
*/

