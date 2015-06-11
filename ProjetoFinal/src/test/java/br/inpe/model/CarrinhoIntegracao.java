package br.inpe.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import br.inpe.observer.AtualizaEstoqueObserverMock;
import br.inpe.observer.EstoqueMock;
import br.inpe.observer.PreferenciasUsuarioObserver;
import br.inpe.service.UsuarioService;

public class CarrinhoIntegracao {
	
	@Rule 
	public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Test
	public void carrinhoComEstoqueEUsuario(){
		//cria objetos
		Produto p = new Produto(4, "Filme", 250.0);
		EstoqueMock.getInstance().addEstoque(p, 10);
		Usuario u = new Usuario(20l, "Fabio");
		Preferencia pref = new Preferencia(u, p);
		
		
		//cria carrinho com observers
		CarrinhoCompras cc = new CarrinhoCompras();
		cc.adicionarObserver(new AtualizaEstoqueObserverMock());
		UsuarioService uServiceMock = ctx.mock(UsuarioService.class);
		ctx.checking(new Expectations() {{
			oneOf(uServiceMock).salvarPreferencia(pref);
		}});
		cc.adicionarObserver(new PreferenciasUsuarioObserver(u, uServiceMock));
		
		cc.addProduto(p, 5);
		cc.removeProduto(p, 3);
		
		assertTrue(u.getPreferencias().contains(pref));
		assertEquals(8, EstoqueMock.getInstance().getQuantidade(p));
		assertEquals(500.0, cc.getTotal(), 0.001);
	}

}
