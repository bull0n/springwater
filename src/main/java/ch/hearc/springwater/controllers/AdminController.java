package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController
{
	@GetMapping(value="/")
	public String getAdmin(Map<String, Object> model)
	{	
		return "admin/admin";
	}
}
