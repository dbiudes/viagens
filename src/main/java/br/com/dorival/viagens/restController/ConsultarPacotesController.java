package br.com.dorival.viagens.restController;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dorival.viagens.dto.ConsultaCidade;
import br.com.dorival.viagens.dto.ConsultaHotel;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.extras.DebugUtil;
import br.com.dorival.viagens.helper.ConsultarPacotesHelper;
import br.com.dorival.viagens.service.ConsultarPacotesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="ConsultarPacotes", description="API de Consulta e Validação de Pacotes de Viagem")
@RestController
//@Validated
public class ConsultarPacotesController {

	@Autowired 
	ConsultarPacotesService consultarPacotesService;
	
	@ApiOperation(value="Consultar pacotes de viagem", httpMethod = "GET", //response=ConsultarPacotesController.class,
				  notes="API para consultar preços de hospedagem nos hotéis de uma cidade. As datas seguem o padrão YYYY-MM-DD.")
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Retorna um JSON com uma lista de hoteis, quartos e preços da cidade informada", response=Diaria.class),
		@ApiResponse(code=204, message="Parâmetros informados incorretamente, favor validar os dados inseridos."),
		@ApiResponse(code=400, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações."),
		@ApiResponse(code=401, message="Não autorizado, é necessário autenticação!"),
		@ApiResponse(code=403, message="Proibido, é necessário permissão de acesso a função!"),
		@ApiResponse(code=404, message="Não encontrado, verifique se o caminho foi informado corretamente ou se existe!"),
		@ApiResponse(code=500, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações.")
	})
    @ApiImplicitParams({@ApiImplicitParam(name = "ID da Cidade", value = "Ex: 1032, 7110 ou 9626", paramType = "path", required = true),
    					@ApiImplicitParam(name = "Data de Checkin", value = "Data de Entrada (yyyy-mm-dd) - Ex: 2018-12-20", paramType = "path", required = true),
    					@ApiImplicitParam(name = "Data de Checkout", value = "Data de Saída (yyyy-mm-dd) - Ex: 2018-12-25", paramType = "path", required = true),
						@ApiImplicitParam(name = "Numero de Adultos", value = "Hóspedes Adultos (1 a 9)", paramType = "path", required = true),
						@ApiImplicitParam(name = "Numero de Criancas", value = "Hóspedes Crianças (0 a 9)", paramType = "path", required = true)
    })
	@RequestMapping(method = RequestMethod.GET, value = "/pacotesporcidade/{ID da Cidade}/{Data de Checkin}/{Data de Checkout}/{Numero de Adultos}/{Numero de Criancas}", produces = "application/json"
			//params = "Numero de Adultos=[1-9]") :[1-9]
			//params = "[ {"in": "path", "name": "username", "type": "string", "default": "foo", "enum":["foo","bar","baz"] }
	)
	@ResponseBody 
	private List<Diaria> consultarPacotesPorCidade(
			@PathVariable("ID da Cidade")		String idCidade,
			@PathVariable("Data de Checkin")	String checkin,
			@PathVariable("Data de Checkout")	String checkout,
			@ApiParam(required = true, allowableValues = "1,2,3,4,5,6,7,8,9",   value = "Numero de Adultos", defaultValue = "1")  @PathVariable("Numero de Adultos")	String adults,
			@ApiParam(required = true, allowableValues = "0,1,2,3,4,5,6,7,8,9", value = "Numero de Criancas", defaultValue = "0") @PathVariable("Numero de Criancas")	String childrens) {
		
		ConsultaCidade consultaCidade = new ConsultaCidade ();
		
		consultaCidade.setConsulta(ConsultarPacotesHelper.DefineValidaConsulta(checkin, checkout, adults, childrens));  
						
		try {
			consultaCidade.setCityCode(idCidade);
		} catch (Exception e) {
			throw new IllegalArgumentException("Código da cidade não informado.");
		}
		DebugUtil.ImprimeMensagem("Pesquisar pacotes e preços na cidade " + consultaCidade.getCityCode());
		DebugUtil.ImprimeParametros(consultaCidade.getConsulta());
		
		return consultarPacotesService.consultaPrecosPorCidade(consultaCidade);
		
	}

	@ApiOperation(value="Validar pacote de viagem", httpMethod = "GET", //response=Consulta.class,
				  notes="API para validar os preços de um hotel específico antes do fechamento do pacote. As datas seguem o padrão YYYY-MM-DD.")
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Retorna um JSON com os quartos e preços do hotel informado"), //response=Diaria.class,
		@ApiResponse(code=204, message="Parâmetros informados incorretamente, favor validar os dados inseridos."),
		@ApiResponse(code=400, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações."),
		@ApiResponse(code=401, message="Não autorizado, é necessário autenticação!"),
		@ApiResponse(code=403, message="Proibido, é necessário permissão de acesso a função!"),
		@ApiResponse(code=404, message="Não encontrado, verifique se o caminho foi informado corretamente ou se existe!"),
		@ApiResponse(code=500, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações.")
	})
    @ApiImplicitParams({@ApiImplicitParam(name = "ID do Hotel", value = "Ex: 100", paramType = "path", required = true), //required = true, dataType = "string", paramType = "query"
    					@ApiImplicitParam(name = "Data de Checkin", value = "Data de Entrada (yyyy-mm-dd) - Ex: 2018-12-20", paramType = "path", required = true),
						@ApiImplicitParam(name = "Data de Checkout", value = "Data de Saída (yyyy-mm-dd) - Ex: 2018-12-25", paramType = "path", required = true),
						@ApiImplicitParam(name = "Numero de Adultos", value = "Hóspedes Adúltos (1 a 4)", paramType = "path", required = true),
						@ApiImplicitParam(name = "Numero de Criancas", value = "Hóspedes Crianças (0 a 9)", paramType = "path", required = true)
    })
	@RequestMapping(method = RequestMethod.GET, value    = "/pacotesporhotel/{ID do Hotel}/{Data de Checkin}/{Data de Checkout}/{Numero de Adultos}/{Numero de Criancas}", produces = "application/json")
	@ResponseBody 
	private List<Diaria> consultarPacotesPorHotel(
		    @ApiParam(value = "name that need to be updated", required = true)  @PathVariable("ID do Hotel") 		String idHotel,
			@PathVariable("Data de Checkin")	String checkin,
			@PathVariable("Data de Checkout")	String checkout,
			@ApiParam(required = true, allowableValues = "1,2,3,4,5,6,7,8,9", 	value = "Numero de Adultos")  @PathVariable("Numero de Adultos")	String adults,
			@ApiParam(required = true, allowableValues = "0,1,2,3,4,5,6,7,8,9", value = "Numero de Criancas") @PathVariable("Numero de Criancas")	String childrens) {

						
		ConsultaHotel consultaHotel = new ConsultaHotel ();
		
		consultaHotel.setConsulta(ConsultarPacotesHelper.DefineValidaConsulta(checkin, checkout, adults, childrens));  
		
		try {
			consultaHotel.setHotelCode(idHotel);
		} catch (Exception e) {
			throw new IllegalArgumentException("Código do hotel não informado.");
		}
		DebugUtil.ImprimeMensagem("Consultar quartos e preços no hotel  : " + consultaHotel.getHotelCode());
		DebugUtil.ImprimeParametros(consultaHotel.getConsulta()); 
		
		return consultarPacotesService.consultaPrecosPorHotel(consultaHotel);
		
	}

}
