package br.inpe.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.inpe.enums.TipoPagamento;
import br.inpe.model.CarrinhoCompras;
import br.inpe.model.Produto;
import br.inpe.model.Usuario;
import br.inpe.service.CarrinhoComprasService;
import br.inpe.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
@Transactional
public class CarrinhoController {
	
	@Autowired
	private CarrinhoComprasService carrinhoComprasService;
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("/adicionar")
	public String adicionar(Produto produto, Integer qtd, HttpSession session){
		Usuario u = (Usuario) session.getAttribute("usuario");
		if(u == null){
			return "/login";
		}
		CarrinhoCompras cc = (CarrinhoCompras) session.getAttribute("carrinho");
		if(cc == null){
			cc = carrinhoComprasService.criarCarrinho(u);
		}
		produto = produtoService.findById(produto.getId());
		cc.addProduto(produto, qtd);
		session.setAttribute("carrinho", cc);
		
		return "redirect:/index";
	}
	
	@RequestMapping("/remove")
	public String remove(Produto produto, Integer qtd, HttpSession session) {
		CarrinhoCompras cc = (CarrinhoCompras) session.getAttribute("carrinho");
		cc.removeProduto(produto, qtd);
		return "redirect:/carrinho/carrinho";
	}
	
	@RequestMapping("/carrinho")
	public String meuCarrinho(){
		return "carrinho/carrinho";
	}
	
	@RequestMapping("/pagar")
	public String pagar(TipoPagamento tipo, HttpSession session){
		CarrinhoCompras cc = (CarrinhoCompras) session.getAttribute("carrinho");
		cc.setPagamento(tipo);
		return "carrinho/pagar";
	}
	
	@RequestMapping("/checkout")
	public String checkout(){
		
		return "carrinho/checkout";
	}

}
