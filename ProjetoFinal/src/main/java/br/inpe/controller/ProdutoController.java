package br.inpe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.inpe.model.Produto;
import br.inpe.model.Usuario;
import br.inpe.service.EstoqueService;
import br.inpe.service.ProdutoService;


@Controller
@RequestMapping("/produto")
@Transactional
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EstoqueService estoqueService;

	@RequestMapping("/list")
	public String execute(Model model, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "adm/login";
		}
		model.addAttribute("produtos", produtoService.findAll());
		
		return "/produto/list";
	}

	@RequestMapping("/remove")
	public String remove(Produto produto, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "adm/login";
		}
		
		produtoService.remover(produto);
		
		return "redirect:/adm";
	}

	
	
	@RequestMapping("/create")
	public String newproduto(Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "adm/login";
		}
		return "produto/create";
	}
	
	@RequestMapping("/update")
	public String updateProduto(Produto produto, Model model, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "adm/login";
		}
		produto = produtoService.findById(produto.getId());
		
		model.addAttribute("produto",produto);
		model.addAttribute("qtd", estoqueService.getQuantidade(produto));
		return "produto/create";
	}

	@RequestMapping("/save")
	public String create(Produto produto, Integer qtd,  HttpSession session,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) {
		
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "adm/login";
		}
		produtoService.salvarOuAtualizar(produto, qtd, image);
		return "redirect:/adm";
	}

	

	@RequestMapping("/image")
	@ResponseBody
	public byte[] image(Produto produto){
		produto = produtoService.findById(produto.getId());
		System.out.println(Base64.encodeBase64String(produto.getPoster()));
		return produto.getPoster();		
	}
	
	@RequestMapping("/estoque/adicionar")
	public String adicionar(Produto produto, Integer qtd, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuarioAdm");
		if(u == null){
			return "rediret/adm/login";
		}
		
		estoqueService.addEstoque(produto, qtd);
		
		return "redirect:/adm";
	}
	
	@ExceptionHandler(Throwable.class)
	public String error(Throwable e, Model model){
		model.addAttribute("mensagem", e.getMessage());
		return "produto/create";
	}

}
