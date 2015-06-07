package br.inpe.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.inpe.model.Produto;
import br.inpe.service.ProdutoService;


@Controller
@RequestMapping("/produto")
@Transactional
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("/list")
	public void execute(Model model) {
		model.addAttribute("produtos", produtoService.findAll());
		System.out.println("Executando o produtos");
	}

	@RequestMapping("/remove")
	public String remove(Produto produto) {
		produtoService.remove(produto);
		return "redirect:list";
	}
	
	@RequestMapping("/create")
	public String newproduto(Model model){
		return "produto/create";
	}

	@RequestMapping("/save")
	public String create(Produto produto, @RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) throws IOException {
		produto.setPoster(image.getBytes());
		produtoService.save(produto);
		return "forward:list";
	}

	@RequestMapping("/image")
	@ResponseBody
	public byte[] image(Produto produto){
		produto = produtoService.findById(produto.getId());
		System.out.println(Base64.encodeBase64String(produto.getPoster()));
		return produto.getPoster();		
	}

}
