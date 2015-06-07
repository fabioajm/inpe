package br.inpe.observer;

import br.inpe.model.Produto;
import br.inpe.observer.CarrinhoObserver;

public class AtualizaEstoqueObserverMock implements CarrinhoObserver {

	@Override
	public void notificarAdicao(Produto p, int qtd) {
		EstoqueMock.getInstance().removeEstoque(p, qtd);
	}

	@Override
	public void notificarRemocao(Produto p, int qtd) {
		EstoqueMock.getInstance().addEstoque(p, qtd);
	}

}
