package br.inpe.observer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.inpe.model.Produto;
import br.inpe.observer.CarrinhoObserver;

public class TestAtualizaEstoqueObserverMock {
	
	private Produto produto = new Produto(1, "Filme", 250.0);
	private CarrinhoObserver co;
	
	@Before
	public void init(){
		int qtd = EstoqueMock.getInstance().getQuantidade(produto);
		EstoqueMock.getInstance().removeEstoque(produto, qtd);
		EstoqueMock.getInstance().addEstoque(produto, 10);
		co = new AtualizaEstoqueObserverMock();
	}
	
	@Test
	public void atualizaEstoqueAdicaoCarrinho(){
		co.notificarAdicao(produto, 5);
		assertEquals(5, EstoqueMock.getInstance().getQuantidade(produto));
	}
	
	@Test
	public void atualizaEstoqueRemocaoCarrinho(){
		co.notificarRemocao(produto, 5);
		assertEquals(15, EstoqueMock.getInstance().getQuantidade(produto));
	}
	
	@Test(expected = RuntimeException.class)
	public void adicaoMaiorQueEstoque(){
		co.notificarAdicao(produto, 15);
	}
			
}
