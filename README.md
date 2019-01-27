# API para Consulta de Diarias de Hoteis

## NOTAS DO PROJETO ##

A aplicação funciona como uma camada de API para atender a uma aplicação cliente na pesquisa dos melhores preços para os hotéis disponíveis. Para isso a API realiza uma pequisa na API do Broker de Hotéis e considerando o número de diárias (entre o checkin e o checkout) e o número de hóspedes (adultos e crianças) processando o valor das diárias para cada um dos tipos de quarto de hotéis disponíveis somando um percentual de comissão (definido em 30%).

```seq {theme="hand"}
Andrew->China: Says Hello 
Note right of China: China thinks\nabout it 
China-->Andrew: How are you? 
Andrew->>China: I am good thanks!
```
Esta aplicação foi construída considerando os seguintes critérios:
 - alta disponibilidade, utilizando uma arquitetura de Micro Serviços baseada em Spring Boot e APIs Rest
 - alta performance, onde cenário de execução paralela foi avaliado para oferecer o menor tempo de resposta
 - agilidade e qualidade de código, utizando SpringBoot com bibliotecas do Java 8 (Stream, Datas, etc)
 - testabilidade, baseando-se na execução de testes unitários com mock de dados

O código está hospedado no GitHub: https://github.com/dbiudes/viagens.git
	  

### COMO EXECUTAR ###

* Se estiver utilizando o *Spring Tool Suite*
	1. Importar o projeto
	2. Clicar com o botão direito sobre o projeto e escolher "Run as + Spring Boot Project"
	
* Se estiver utilizando o *Eclipse (tradicional)*
	1. Importar o projeto
	2. Compilar o projeto
	3. Abrir um terminal (no Eclipse ou Externo) a partir da raiz do projeto
	4. Executar com o comando "mvn clean package && java -jar target/web-jax-rs-0.0.1-SNAPSHOT.jar"


### COMO VALIDAR ###
 
1. Utilizando o Swagger no endereço http://localhost:8080/swagger-ui.html (a porta está definida no "application.properties")
	
2. No browser ou via SoapUI (GET) chamar o endereço abaixo (porta definida no "application.properties")
http://localhost:8080/pacotesporcidade/1032/2017-05-20/2017-05-25/2/3
considerando os parametos /pacotesporcidade/{cidade}/{checkin}/{checkout}/{adults}/{childrens}
API DO BROKER: https://cvcbackendhotel.herokuapp.com/hotels/avail/{ID_da_Cidade}
resultado esperado: JSON com lista de hoteis e preços dos pacotes por_tipo_quarto = ((adultos + crianças) * diarias) + 30%

3. No browser ou via SoapUI (GET) chamar o endereço abaixo (porta definida no "application.properties")
http://localhost:8080/pacotesporhotel/100/2017-05-20/2017-05-25/2/3
considerando os parametos /pacotesporhotel/{idHotel}/{checkin}/{checkout}/{adults}/{childrens}
API DO BROKER: https://cvcbackendhotel.herokuapp.com/hotels/{ID_do_Hotel}
resultado esperado: JSON com um unico hotel com preços dos pacotes por_tipo_quarto = ((adultos + crianças) * diarias) + 30%
			
4. No Eclipse ou no Spring Tool Suite clicando com o botão direito do mouse sobre a classe ApiViagensApplicationTests.java e selecionando "Run as + JUnit Test". Todos os testes unitários devem processar sem erros.
	 
