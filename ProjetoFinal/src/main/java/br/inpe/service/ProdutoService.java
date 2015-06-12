package br.inpe.service;

import java.util.List;

import br.inpe.model.Produto;

public interface ProdutoService {

	public void save(Produto p);

	public List<Produto> findAll();

	public Produto findById(Integer id);

	public Produto salvarOuAtualizar(Produto produto, Integer qtd, byte[] image);

	public void remover(Produto produto);

}