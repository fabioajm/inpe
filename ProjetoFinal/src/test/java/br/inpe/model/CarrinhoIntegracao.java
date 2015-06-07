package br.inpe.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.inpe.observer.AtualizaEstoqueObserverMock;
import br.inpe.observer.EstoqueMock;
import br.inpe.observer.PreferenciasUsuarioObserver;

public class CarrinhoIntegracao {
	
	@Test
	public void carrinhoComEstoqueEUsuario(){
		//cria objetos
		Produto p = new Produto(4, "Filme", 250.0);
		EstoqueMock.getInstance().addEstoque(p, 10);
		Usuario u = new Usuario("Fabio");
		
		//cria carrinho com observers
		CarrinhoCompras cc = new CarrinhoCompras();
		cc.adicionarObserver(new AtualizaEstoqueObserverMock());
		cc.adicionarObserver(new PreferenciasUsuarioObserver(u));
		
		cc.addProduto(p, 5);
		cc.removeProduto(p, 3);
		
		assertTrue(u.getPreferencias().contains("Filme"));
		assertEquals(8, EstoqueMock.getInstance().getQuantidade(p));
		assertEquals(500.0, cc.total(), 0.001);
	}

}
