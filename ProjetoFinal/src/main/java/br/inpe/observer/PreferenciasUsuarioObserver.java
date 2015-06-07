package br.inpe.observer;

import br.inpe.model.Produto;
import br.inpe.model.Usuario;

public class PreferenciasUsuarioObserver implements CarrinhoObserver {

	private Usuario usuario;

	public PreferenciasUsuarioObserver(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void notificarAdicao(Produto p, int qtd) {
		usuario.addPreferncia(p.getNome());
	}

	@Override
	public void notificarRemocao(Produto p, int qtd) {

	}

}
