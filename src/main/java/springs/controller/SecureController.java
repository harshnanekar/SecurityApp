package springs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class SecureController {

	@GetMapping("/index")
	public String gets() {
		return "index";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	@GetMapping("/clogout")
	public String clogout() {
		return "index";
	}
}
