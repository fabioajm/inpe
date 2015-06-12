package br.inpe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.model.Produto;
import br.inpe.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstoqueService estoqueService;

	@Override
	public void save(Produto p) {
		produtoRepository.save(p);
	}

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto findById(Integer id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Produto salvarOuAtualizar(Produto produto, Integer qtd, byte[] image) {
		setarImagem(produto, image);
		salvarOuAtualizar(produto);
		atualizarEstoque(produto, qtd);
		return produto;
	}

	private void atualizarEstoque(Produto produto, Integer qtd) {
		removerTodoEstoque(produto, produto);
		estoqueService.addEstoque(produto, qtd);
	}

	private void salvarOuAtualizar(Produto produto) {
		if (produto.getId() == null) {
			produtoRepository.save(produto);
		} else {
			produtoRepository.merge(produto);
		}
	}

	private void setarImagem(Produto produto, byte[] image) {
		if (manterImagemAtual(produto, image)) {
			Produto p = produtoRepository.findById(produto.getId());
			produto.setPoster(p.getPoster());
		} else {
			produto.setPoster(image);
		}
	}

	private boolean manterImagemAtual(Produto produto, byte[] image) {
		return (image == null || image.length < 1) && produto.getId() != null;
	}

	private void removerTodoEstoque(Produto produto, Produto p) {
		int quantidade = estoqueService.getQuantidade(p);
		if (quantidade > 0)
			estoqueService.removeEstoque(produto, quantidade);
	}

	@Override
	public void remover(Produto produto) {
		produto = produtoRepository.findById(produto.getId());
		removerTodoEstoque(produto, produto);
		produtoRepository.remove(produto);
	}

}
