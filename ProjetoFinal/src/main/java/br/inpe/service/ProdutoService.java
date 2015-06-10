package br.inpe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.model.Produto;
import br.inpe.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void save(Produto p){
		produtoRepository.save(p);
	}
	
	public void saveOrUpdate(Produto p){
		if(p.getId() == null){
			produtoRepository.save(p);
		}else {
			produtoRepository.update(p);
		}
	}

	public List<Produto> findAll() {
		return produtoRepository.buscarProdutosComEstoque();
	}

	public void remove(Produto produto) {
		produtoRepository.remove(produto);
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id);
	}
	

}
