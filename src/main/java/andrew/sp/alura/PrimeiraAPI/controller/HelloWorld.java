package andrew.sp.alura.PrimeiraAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {
	
	@RequestMapping("/teste")
	@ResponseBody
	public String hello() {
		return "Hello World";
	}
}
