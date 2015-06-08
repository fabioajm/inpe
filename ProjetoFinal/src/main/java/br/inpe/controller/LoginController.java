package br.inpe.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.exception.UsuarioNaoEncontradoException;
import br.inpe.model.CarrinhoCompras;
import br.inpe.model.Usuario;
import br.inpe.service.UsuarioService;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/efetuarLogin")
	public String efetuarLogin(Usuario usuario, Model model, HttpSession session){
		try{
		session.setAttribute("usuario", usuarioService.logar(usuario));
		}catch(UsuarioNaoEncontradoException e){
			model.addAttribute("mensagem", e.getMessage());
			return "/login";
		}
		return "redirect:index";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("usuario");
		CarrinhoCompras cc=  (CarrinhoCompras) session.getAttribute("carrinho");
		cc.esvaziar();
		session.removeAttribute("carrinho");
		return "redirect:index";
	}
	
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/usuario/save")
	public String save(Usuario usuario, HttpSession session){
		usuarioService.save(usuario);
		session.setAttribute("usuario", usuario);
		return "index";
	}

}
