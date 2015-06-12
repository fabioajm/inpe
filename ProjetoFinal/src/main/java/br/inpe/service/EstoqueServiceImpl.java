package br.inpe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.exception.NaoTemProdutoNoEstoqueException;
import br.inpe.model.Estoque;
import br.inpe.model.Produto;
import br.inpe.repository.EstoqueRepository;


@Service
@Transactional
public class EstoqueServiceImpl implements EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Override
	public void addEstoque(Produto p, int qtd){
		Estoque e = estoqueRepository.getEstoque(p);
		if(e != null){
			qtd += e.getQuantidade();
			e.setQuantidade(qtd);
			estoqueRepository.merge(e);
		}else{
			e = new Estoque();
			e.setProduto(p);
			e.setQuantidade(qtd);
			estoqueRepository.save(e);
		}
	}
	@Override
	public void removeEstoque(Produto p, int qtd){
		Estoque e = estoqueRepository.getEstoque(p);
		if (e == null || e.getQuantidade() < qtd) {
			throw new NaoTemProdutoNoEstoqueException("Não tem estoque para remover");
		}
		if (e.getQuantidade() == qtd) {
			estoqueRepository.remove(e);
		} else {
			qtd = e.getQuantidade() - qtd;
			e.setQuantidade(qtd);
			estoqueRepository.merge(e);
		}
	}
	
	@Override
	public int getQuantidade(Produto p){
		Estoque estoque = estoqueRepository.getEstoque(p);
		if(estoque != null)
			return estoque.getQuantidade();
		return 0;
	}
	@Override
	public List<Estoque> findAll() {
		return estoqueRepository.findAll();
	}
}
