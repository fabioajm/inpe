package br.inpe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.model.Usuario;
import br.inpe.service.EstoqueService;
import br.inpe.service.ProdutoService;

@Controller
public class MainController  {
		
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("estoques", estoqueService.findAll());
		return "index";
	}
	
	@RequestMapping("/adm")
	public String adm(Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "adm/login";
		}
		
		model.addAttribute("estoques", estoqueService.findAll());
		
		return "adm/index";
	}

}
