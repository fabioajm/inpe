package br.inpe.observer;

import br.inpe.model.Produto;

public interface CarrinhoObserver {

	public abstract void notificarAdicao(Produto p, int qtd);

	public abstract void notificarRemocao(Produto p, int qtd);

}
