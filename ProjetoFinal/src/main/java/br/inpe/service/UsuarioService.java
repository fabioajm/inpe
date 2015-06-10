package br.inpe.service;

import br.inpe.model.Usuario;

public interface UsuarioService {

	public abstract void save(Usuario u);

	public abstract Usuario buscarPorLogin(String login);

	public abstract Usuario logar(Usuario u);

	public abstract Usuario find(Long id);

	public abstract void merge(Usuario usuario);

}