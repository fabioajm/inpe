package br.inpe.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.inpe.exception.UsuarioException;
import br.inpe.model.Usuario;

@Repository
public class UsuarioRepository extends GenericRepository<Usuario, Long> {

	public Usuario buscarPorLogin(String login) {
		Query query = entityManager.createQuery("from Usuario u where u.login= :login ");
		query.setParameter("login", login);
		try{
			return (Usuario) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public Usuario logar(Usuario u) {
		Query query = entityManager.createQuery("from Usuario u where u.login= :login and u.senha= :senha");
		query.setParameter("login", u.getLogin());
		query.setParameter("senha", u.getSenha());
		try{
			return (Usuario) query.getSingleResult();
		}catch(NoResultException e){
			throw new UsuarioException("Usuario ou senha inválidos");
		}
	}

}
