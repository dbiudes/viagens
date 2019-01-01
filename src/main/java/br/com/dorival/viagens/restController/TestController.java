package br.com.dorival.viagens.restController;
import br.com.dorival.viagens.dto.*;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	private static final String welcomemsg = "Bemvindo %s!";

	@GetMapping("/test")
	@ResponseBody
	public TestOutput welcomeUser(@RequestParam(name="name", required=false, defaultValue="Java Fan") String name) {
		if(name.equals("teste") || name.equals("test")) {
			throw new IllegalArgumentException("error.username");
		}
		return new TestOutput(String.format(welcomemsg, name));
	}
	
	@ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
    }
	
}
