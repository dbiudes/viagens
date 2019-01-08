package br.com.dorival.viagens.restController;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.dorival.viagens.dto.Diaria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


//@Validated
@Api(value="ConsultarPacotes", description="API de Consulta e Validação de Pacotes de Viagem")
public interface ConsultarPacotesAPI {
	
	
	
	@ApiOperation(value="Consultar pacotes de viagem", httpMethod = "GET", //response=ConsultarPacotesController.class,
			  notes="API para consultar preços de hospedagem nos hotéis de uma cidade. As datas seguem o padrão YYYY-MM-DD.")
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Retorna um JSON com uma lista de hoteis, quartos e preços da cidade informada"),
		@ApiResponse(code=204, message="Parâmetros informados incorretamente, favor validar os dados inseridos."),
		@ApiResponse(code=400, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações."),
		@ApiResponse(code=401, message="Não autorizado, é necessário autenticação!"),
		@ApiResponse(code=403, message="Proibido, é necessário permissão de acesso a função!"),
		@ApiResponse(code=404, message="Não encontrado, verifique se o caminho foi informado corretamente ou se existe!"),
		@ApiResponse(code=500, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações.")
	})
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", required = true, name = "ID da Cidade", 		value = "Ex: 1032, 7110 ou 9626"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Data de Checkin", 	value = "Data de Entrada (yyyy-mm-dd) - Ex: 2018-12-20"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Data de Checkout", 	value = "Data de Saída (yyyy-mm-dd) - Ex: 2018-12-25"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Numero de Adultos", 	value = "Hóspedes Adultos (1 a 9)"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Numero de Criancas", value = "Hóspedes Crianças (0 a 9)")
	})
	@RequestMapping(method = RequestMethod.GET, value = "/pacotesporcidade/{ID da Cidade}/{Data de Checkin}/{Data de Checkout}/{Numero de Adultos}/{Numero de Criancas}", produces = "application/json"
			//params = "Numero de Adultos=[1-9]") :[1-9]
			//params = "[ {"in": "path", "name": "username", "type": "string", "default": "foo", "enum":["foo","bar","baz"] }
	)
	@ResponseBody 
	List<Diaria> consultarPacotesPorCidade(
		@ApiParam(required = true, 											value = "Código do cidade")  						@PathVariable("ID da Cidade")		String idCidade,
																																@PathVariable("Data de Checkin")	String checkin,
																																@PathVariable("Data de Checkout")	String checkout,
		@ApiParam(required = true, allowableValues = "1,2,3,4,5,6,7,8,9",   value = "Numero de Adultos", defaultValue = "1") 	@PathVariable("Numero de Adultos")	String adults,
		@ApiParam(required = true, allowableValues = "0,1,2,3,4,5,6,7,8,9", value = "Numero de Criancas", defaultValue = "0") 	@PathVariable("Numero de Criancas")	String childrens) ;
	
	
	
	
	//  -------------------------------------------------------------------------------
	
	
	
	@ApiOperation(value="Validar pacote de viagem", httpMethod = "GET", //response=Consulta.class,
			  notes="API para validar os preços de um hotel específico antes do fechamento do pacote. As datas seguem o padrão YYYY-MM-DD.")
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Retorna um JSON com os quartos e preços do hotel informado."), //response=Diaria.class,
		@ApiResponse(code=204, message="Parâmetros informados incorretamente, favor validar os dados inseridos."),
		@ApiResponse(code=400, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações."),
		@ApiResponse(code=401, message="Não autorizado, é necessário autenticação!"),
		@ApiResponse(code=403, message="Proibido, é necessário permissão de acesso a função!"),
		@ApiResponse(code=404, message="Não encontrado, verifique se o caminho foi informado corretamente ou se existe!"),
		@ApiResponse(code=500, message="Erros internos causados pelo consumidor (client) do Microserviço, como erros de validação ou inconsistência de informações.")
	})
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", required = true, name = "ID do Hotel", 		value = "Ex: 100"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Data de Checkin", 	value = "Data de Entrada (yyyy-mm-dd) - Ex: 2018-12-20"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Data de Checkout", 	value = "Data de Saída (yyyy-mm-dd) - Ex: 2018-12-25"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Numero de Adultos", 	value = "Hóspedes Adúltos (1 a 9)"),
						@ApiImplicitParam(paramType = "path", required = true, name = "Numero de Criancas", value = "Hóspedes Crianças (0 a 9)")
	})
	@RequestMapping(method = RequestMethod.GET, value = "/pacotesporhotel/{ID do Hotel}/{Data de Checkin}/{Data de Checkout}/{Numero de Adultos}/{Numero de Criancas}", produces = "application/json")
	@ResponseBody 
	List<Diaria> consultarPacotesPorHotel(
	    @ApiParam(required = true, 											value = "Código do hotel")  						@PathVariable("ID do Hotel") 		String idHotel,
																																@PathVariable("Data de Checkin")	String checkin,
																																@PathVariable("Data de Checkout")	String checkout,
		@ApiParam(required = true, allowableValues = "1,2,3,4,5,6,7,8,9", 	value = "Numero de Adultos",  defaultValue = "1") 	@PathVariable("Numero de Adultos")	String adults,
		@ApiParam(required = true, allowableValues = "0,1,2,3,4,5,6,7,8,9", value = "Numero de Criancas", defaultValue = "0") 	@PathVariable("Numero de Criancas")	String childrens);


}

