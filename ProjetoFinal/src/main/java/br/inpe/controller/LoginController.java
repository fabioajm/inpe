package br.inpe.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.model.Customer;
import br.inpe.repository.MovieRepository;
import br.inpe.service.ProdutoService;

@Controller
@Transactional
public class LoginController {
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Customer customer, HttpSession session){
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("produtos", produtoService.findAll());
		return "index";
	}
	

}
