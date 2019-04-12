package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
	@GetMapping(value="/")
	public String getHome(Map<String, Object> model)
	{	
		return "redirect:/boisson/";
	}
}
