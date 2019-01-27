# API para Consulta de Hoteis e Diarias


NOTAS DO PROJETO

1. RESUMO

A aplicação servirá como uma camada de API para fornecer dados originados a partir de broker de hotéis e processamento do cálculo do
valor dos pacotes por quarto de hotel considerando o número de diárias e o número de hóspedes somados ao percentual de comissão (30%).

Esta aplicação foi construída considerando os seguintes critérios: 
 - alta disponibilidade, em uma arquitetura de Micro Serviços baseada em APIs Rest 
 - alta performance, incluindo testes de execução paralela para definir o melhor cenário de código
 - qualidade de código, utizando a plataforma SpringBoot com bibliotecas do Java 8 (Stream, Datas, etc)  
 - testabilidade, baseando-se na execução de testes unitários com mock de dados

O código está hospedado no GitHub: https://github.com/dbiudes/viagens.git
	  

2. COMO EXECUTAR

	a)  No Eclipse, após importar o projeto
		Abrir um terminal (no Eclipse ou Externo) a partir da raiz do projeto
		Compilar e executar com o comando (substituir "web-jax-rs" pelo nome do seu projeto):
			mvn clean package && java -jar target/web-jax-rs-0.0.1-SNAPSHOT.jar
	b)  No Spring Tool Suite, após importar o projeto
		Clicar com o botão direito sobre o projeto e escolher "Run as + Spring Boot Project"

3. COMO VALIDAR 
 
	a)  Utilizando o Swagger no endereço abaixo (porta definida no "application.properties") 
		http://localhost:8080/swagger-ui.html
	
	b)  No browser ou via SoapUI (GET) chamar o endereço abaixo (porta definida no "application.properties")
		http://localhost:8080/pacotesporcidade/1032/2017-05-20/2017-05-25/2/3
		considerando os parametos /pacotesporcidade/{cidade}/{checkin}/{checkout}/{adults}/{childrens}
		API DO BROKER: https://cvcbackendhotel.herokuapp.com/hotels/avail/{ID_da_Cidade}
		resultado esperado: JSON com lista de hoteis e preços dos pacotes por_tipo_quarto = ((adultos + crianças) * diarias) + 30%

	c)  No browser ou via SoapUI (GET) chamar o endereço abaixo (porta definida no "application.properties")
		http://localhost:8080/pacotesporhotel/100/2017-05-20/2017-05-25/2/3
		considerando os parametos /pacotesporhotel/{idHotel}/{checkin}/{checkout}/{adults}/{childrens}
		API DO BROKER: https://cvcbackendhotel.herokuapp.com/hotels/{ID_do_Hotel}
		resultado esperado: JSON com um unico hotel com preços dos pacotes por_tipo_quarto = ((adultos + crianças) * diarias) + 30%
			
	d)  No Eclipse ou no Spring Tool Suite clicando com o botão direito do mouse sobre a classe ApiViagensApplicationTests.java 
	    e selecionando "Run as + JUnit Test". Todos os testes unitários devem processar sem erros.
	 
