package br.inpe.service;

import java.util.List;

import br.inpe.model.Estoque;
import br.inpe.model.Produto;

public interface EstoqueService {

	public void addEstoque(Produto p, int qtd);

	public void removeEstoque(Produto p, int qtd);

	public int getQuantidade(Produto p);

	public List<Estoque> findAll();

}