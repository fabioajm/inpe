package br.inpe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.exception.UsuarioException;
import br.inpe.model.Usuario;
import br.inpe.repository.UsuarioRepository;

@Service
@Transactional(dontRollbackOn=UsuarioException.class)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void save(Usuario u) {
			if(usuarioRepository.buscarPorLogin(u.getLogin()) == null){
				usuarioRepository.save(u);
			}else{
				throw new UsuarioException("Login já existente");
			}
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
