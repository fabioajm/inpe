package br.inpe.observer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.inpe.model.Produto;
import br.inpe.model.Usuario;

public class TestPreferenciasUsuarioObserver {

	@Test
	public void adicaoPrefernciasUsuario(){
		Usuario u = new Usuario("fabio");
		Produto p = new Produto(1, "Filme", 250.0);
		CarrinhoObserver co = new PreferenciasUsuarioObserver(u);
		co.notificarAdicao(p, 5);
		assertTrue(u.getPreferencias().contains("Filme"));
	}
}
