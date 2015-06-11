package br.inpe.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.inpe.model.Produto;
import br.inpe.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstoqueService estoqueService;
	
	public void save(Produto p){
		produtoRepository.save(p);
	}
	

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id);
	}

	public void salvarOuAtualizar(Produto produto, Integer qtd,	MultipartFile image) {
			if(produto.getId() != null){
				Produto p = produtoRepository.findById(produto.getId());
				int quantidade = estoqueService.getQuantidade(p);
				estoqueService.removeEstoque(produto, quantidade);
				produto.setPoster(p.getPoster());
			}
			try {
				if(image.getBytes() != null && image.getBytes().length > 0){
					produto.setPoster(image.getBytes());
				}
			} catch (IOException e) {
			}
			
			if(produto.getId() == null){
				produtoRepository.save(produto);
			}else {
				produtoRepository.merger(produto);
			}
			estoqueService.addEstoque(produto, qtd);
	}
	

	public void remover(Produto produto) {
		produto = produtoRepository.findById(produto.getId());
		int quantidade = estoqueService.getQuantidade(produto);
		estoqueService.removeEstoque(produto, quantidade);
		produtoRepository.remove(produto);
	}

}
