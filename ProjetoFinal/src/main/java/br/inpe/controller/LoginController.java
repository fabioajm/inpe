package br.inpe.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.model.Customer;

@Controller
@Transactional
public class LoginController {
	

	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Customer customer, HttpSession session){
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

}
