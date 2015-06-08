package br.inpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.service.ProdutoService;

@Controller
public class MainController  {
		
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("produtos", produtoService.findAll());
		return "index";
	}

}
