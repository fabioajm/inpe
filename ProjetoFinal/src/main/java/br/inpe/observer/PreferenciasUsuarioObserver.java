package br.inpe.observer;

import br.inpe.model.Preferencia;
import br.inpe.model.Produto;
import br.inpe.model.Usuario;
import br.inpe.service.UsuarioService;

public class PreferenciasUsuarioObserver implements CarrinhoObserver {


	private Usuario usuario;
	private UsuarioService usuarioService;


	public PreferenciasUsuarioObserver(Usuario usuario, UsuarioService usuarioService) {
		this.usuario = usuario;
		this.usuarioService = usuarioService;
	}

	@Override
	public void notificarAdicao(Produto p, int qtd) {
		Preferencia pref = new Preferencia(usuarioService.find(usuario.getId()), p);
		usuario.addPreferncia(pref);
		usuarioService.salvarPreferencia(pref);
	}

	@Override
	public void notificarRemocao(Produto p, int qtd) {

	}

}
