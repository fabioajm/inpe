package br.inpe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inpe.exception.UsuarioException;
import br.inpe.model.Preferencia;
import br.inpe.model.Usuario;
import br.inpe.repository.PreferenciaRepository;
import br.inpe.repository.ProdutoRepository;
import br.inpe.repository.UsuarioRepository;

@Service
@Transactional(dontRollbackOn=UsuarioException.class)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PreferenciaRepository preferenciaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public void save(Usuario u) {
			if(usuarioRepository.buscarPorLogin(u.getLogin()) == null){
				usuarioRepository.save(u);
			}else{
				throw new UsuarioException("Login já existente");
			}
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.buscarPorLogin(login);
	}

	@Override
	public Usuario logar(Usuario u) {
		u = usuarioRepository.logar(u);
		u.setPreferencias(preferenciaRepository.getPreferecias(u));
		return u;
	}

	@Override
	public Usuario find(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public void merge(Usuario usuario) {
		usuarioRepository.merge(usuario);
	}

	@Override
	public void salvarPreferencia(Preferencia pref) {
		pref.setProduto(produtoRepository.getReference(pref.getProduto().getId()));
		pref.setUsuario(usuarioRepository.getReference(pref.getUsuario().getId()));
		preferenciaRepository.save(pref);
	}

}
