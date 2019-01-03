package br.com.dorival.viagens.extras;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))	
          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud"))) 
          .paths(Predicates.not(PathSelectors.regex("/error")))
          .build()
          .apiInfo(this.informacoesApi().build());                          												
    }
	
	private ApiInfoBuilder informacoesApi() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("REST API - PACOTES DE VIAGENS");
		apiInfoBuilder.description(
				"API para consultar os preços dos pacotes de viagens nos hoteis de um deteminado estado " + 
				"e validar o preço vigente em um determinado hotel antes de sua contratação.\n " +
				"São considerados o número de hospedes (adultos e crianças) e o número de diárias do período informado na consulta." + 
				"\n\n Desenvolvido por  Dorival Biudes - dorival@my.com");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("swagger-ui.html#");
		apiInfoBuilder.license("GitHub");
		apiInfoBuilder.licenseUrl("https://github.com/dbiudes");
 
		return apiInfoBuilder;
 
	}

	
}