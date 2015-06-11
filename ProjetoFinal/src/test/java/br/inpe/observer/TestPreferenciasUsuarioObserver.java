package br.inpe.observer;

import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import br.inpe.model.Produto;
import br.inpe.model.Usuario;
import br.inpe.service.UsuarioService;

public class TestPreferenciasUsuarioObserver {
	
	@Rule 
	public JUnitRuleMockery ctx = new JUnitRuleMockery();

	@Test
	public void adicaoPrefernciasUsuario(){
		Usuario u = new Usuario("fabio");
		Produto p = new Produto(1, "Filme", 250.0);
		UsuarioService uServiceMock = ctx.mock(UsuarioService.class);
		ctx.checking(new Expectations() {{
			oneOf(uServiceMock).merge(u);
		}});
		CarrinhoObserver co = new PreferenciasUsuarioObserver(u, uServiceMock);
		co.notificarAdicao(p, 5);
		assertTrue(u.getPreferencias().contains(p));
	}
}
