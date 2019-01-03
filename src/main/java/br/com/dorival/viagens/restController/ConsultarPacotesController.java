package br.com.dorival.viagens.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dorival.viagens.dto.Consulta;
import br.com.dorival.viagens.dto.Diaria;
import br.com.dorival.viagens.extras.DebugUtil;
import br.com.dorival.viagens.helper.ConsultarPacotesHelper;
import br.com.dorival.viagens.service.ConsultarPacotesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="ConsultarPacotes", description="API de Consulta e Validação de Pacotes de Viagem")
@RestController
//@Validated
public class ConsultarPacotesController {

	@Autowired 
	ConsultarPacotesService consultarPacotesService;
	
	//@Autowired 
	Consulta consulta = new Consulta();
		
	@ApiOperation(value="Consultar pacotes de viagem", httpMethod = "GET", //response=ConsultarPacotesController.class,
				  notes="API para consultar preços de hospedagem nos hotéis de uma cidade. As datas seguem o padrão YYYY-MM-DD.")
	@ApiResponses(value= {
		@ApiResponse(code=200,response=Diaria.class, message="Retorna um JSON com uma lista de hoteis, quartos e preços da cidade informada"),
		@ApiResponse(code=400, message="Parâmetros informados incorretamente"),
		@ApiResponse(code=401, message="Acesso não autorizado"),
		@ApiResponse(code=403, message="Validar chamada da API"),
		@ApiResponse(code=404, message="Resultado não encontrado"),
		@ApiResponse(code=500, message="Erro no processamento")
	})
    @ApiImplicitParams({@ApiImplicitParam(name = "ID da Cidade", value = "Ex: 1032, 7110 ou 9626", paramType = "path"),
    					@ApiImplicitParam(name = "Data de Checkin", value = "Data de Entrada (yyyy-mm-dd) - Ex: 2018-12-20", paramType = "path"),
    					@ApiImplicitParam(name = "Data de Checkout", value = "Data de Saída (yyyy-mm-dd) - Ex: 2018-12-25", paramType = "path"),
						@ApiImplicitParam(name = "Numero de Adultos", value = "Hóspedes Adultos (1 a 9)", paramType = "path"),
						@ApiImplicitParam(name = "Numero de Criancas", value = "Hóspedes Crianças (0 a 9)", paramType = "path")
    })
	@RequestMapping(method = RequestMethod.GET, value = "/pacotesporcidade/{ID da Cidade}/{Data de Checkin}/{Data de Checkout}/{Numero de Adultos}/{Numero de Criancas}", produces = "application/json")
	@ResponseBody 
	private List<Diaria> consultarPacotesPorCidade(
			@PathVariable("ID da Cidade")		String idCidade,
			@PathVariable("Data de Checkin")	String checkin,
			@PathVariable("Data de Checkout")	String checkout,
			@PathVariable("Numero de Adultos")	String adults,
			@PathVariable("Numero de Criancas")	String childrens) {
				
		this.consulta = ConsultarPacotesHelper.DefineValidaConsulta(checkin, checkout, adults, childrens);
		try {
			this.consulta.setCityCode(idCidade);
		} catch (Exception e) {
			throw new IllegalArgumentException("Código da cidade não informado.");
		}
		DebugUtil.ImprimeMensagem("Pesquisar Pacotes por Cidade  : " + consulta.getCityCode());
		DebugUtil.ImprimeParametros(consulta);
		
		return consultarPacotesService.consultaPrecosPorCidade(consulta);
		
	}

	@ApiOperation(value="Validar pacote de viagem", httpMethod = "GET", //response=Consulta.class,
				  notes="API para validar os preços de um hotel específico antes do fechamento do pacote. As datas seguem o padrão YYYY-MM-DD.")
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Retorna um JSON com os quartos e preços do hotel informado"), //response=Diaria.class, 
		@ApiResponse(code=400, message="Parâmetros informados incorretamente"),
		@ApiResponse(code=401, message="Acesso não autorizado"),
		@ApiResponse(code=403, message="Validar chamada da API"),
		@ApiResponse(code=404, message="Resultado não encontrado"),
		@ApiResponse(code=500, message="Erro no processamento") 
	})
    @ApiImplicitParams({@ApiImplicitParam(name = "ID do Hotel", value = "Ex: 100", paramType = "path"),
    					@ApiImplicitParam(name = "Data de Checkin", value = "Data de Entrada (yyyy-mm-dd) - Ex: 2018-12-20", paramType = "path"),
						@ApiImplicitParam(name = "Data de Checkout", value = "Data de Saída (yyyy-mm-dd) - Ex: 2018-12-25", paramType = "path"),
						@ApiImplicitParam(name = "Numero de Adultos", value = "Hóspedes Adúltos (1 a 4)", paramType = "path"),
						@ApiImplicitParam(name = "Numero de Criancas", value = "Hóspedes Crianças (0 a 9)", paramType = "path")
    })
	@RequestMapping(method = RequestMethod.GET, 
		value = "/pacotesporhotel/{ID do Hotel}/{Data de Checkin}/{Data de Checkout}/{Numero de Adultos}/{Numero de Criancas}", produces = "application/json") //params = "Numero de Adultos=[1-9]") :[1-9]
		
	@ResponseBody 
	private List<Diaria> consultarPacotesPorHotel(
			@PathVariable("ID do Hotel") 		String idHotel,
			@PathVariable("Data de Checkin")	String checkin,
			@PathVariable("Data de Checkout")	String checkout,
			@PathVariable("Numero de Adultos")	String adults,
			@PathVariable("Numero de Criancas")	String childrens) {
		
		this.consulta = ConsultarPacotesHelper.DefineValidaConsulta(checkin, checkout, adults, childrens);
		try {
			consulta.setHotelCode(idHotel);
		} catch (Exception e) {
			throw new IllegalArgumentException("Código do hotel não informado.");
		}
		DebugUtil.ImprimeMensagem("Pesquisar Pacotes por Hotel  : " + consulta.getHotelCode());
		DebugUtil.ImprimeParametros(consulta); 
		
		return consultarPacotesService.consultaPrecosPorHotel(consulta);
		
	}

}
