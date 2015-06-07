package br.inpe.observer;

import static org.junit.Assert.*;
import br.inpe.model.Produto;
import br.inpe.observer.CarrinhoObserver;

public class MockCarrinhoObserver implements CarrinhoObserver {
	
	private Produto adicionado;
	private int qtdAdcionado;
	private Produto removido;
	private int qtdRemovida;

	@Override
	public void notificarAdicao(Produto p, int qtd){
		this.adicionado = p;
		this.qtdAdcionado = qtd;
	}
	@Override
	public void notificarRemocao(Produto p, int qtd){
		this.removido = p;
		this.qtdRemovida = qtd;
	}

	public void verficarNotificacaoAdicao(Produto p, int qtd) {
		assertEquals(adicionado, p);
		assertEquals(qtdAdcionado, qtd);
		
	}

	public void verficarNotificacaoRemocao(Produto p, int qtd) {
		assertEquals(removido, p);
		assertEquals(qtdRemovida, qtd);
	}

}
