package br.inpe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.exception.UsuarioNaoEncontradoException;
import br.inpe.model.Usuario;
import br.inpe.repository.UsuarioRepository;

@Service
@Transactional(dontRollbackOn=UsuarioNaoEncontradoException.class)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void save(Usuario u) {
		usuarioRepository.save(u);
	}

	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.buscarPorLogin(login);
	}

	public Usuario logar(Usuario u) {
		return usuarioRepository.logar(u);
	}

	public Usuario find(Long id) {
		return usuarioRepository.findById(id);
	}

}
