package br.inpe.service;

import br.inpe.model.Produto;

public interface EstoqueService {

	public void addEstoque(Produto p, int qtd);

	public void removeEstoque(Produto p, int qtd);

	public int getQuantidade(Produto p);

}