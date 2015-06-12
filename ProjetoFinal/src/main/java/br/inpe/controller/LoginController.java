package br.inpe.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.exception.UsuarioException;
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
		}catch(UsuarioException e){
			model.addAttribute("mensagem", e.getMessage());
			return "/login";
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("usuario");
		CarrinhoCompras cc=  (CarrinhoCompras) session.getAttribute("carrinho");
		if(cc != null){
			cc.esvaziar();
			session.removeAttribute("carrinho");
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/adm/efetuarLogin")
	public String efetuarLoginAdm(Usuario usuario, Model model, HttpSession session){
		try{
		session.setAttribute("usuarioAdm", usuarioService.logar(usuario));
		}catch(UsuarioException e){
			model.addAttribute("mensagem", e.getMessage());
			return "/adm/login";
		}
		return "redirect:/adm";
	}
	
	@RequestMapping("/adm/logout")
	public String logoutAdm(HttpSession session){
		session.removeAttribute("usuarioAdm");
		return "redirect:/adm";
	}
	
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/usuario/save")
	public String save(Usuario usuario, Model model, HttpSession session){
		try{
			usuarioService.save(usuario);
			session.setAttribute("usuario", usuario);
			return "redirect:/index";
		}catch(UsuarioException e){
			model.addAttribute("mensagem", e.getMessage());
			return "/login";
		}
	}
	
	@RequestMapping("/adm/usuario/save")
	public String saveAdm(Usuario usuario, Model model, HttpSession session){
		try{
			usuarioService.save(usuario);
			session.setAttribute("usuarioAdm", usuario);
			return "redirect:/adm";
		}catch(UsuarioException e){
			model.addAttribute("mensagem", e.getMessage());
			return "/adm";
		}
	}

}
