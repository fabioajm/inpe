package br.inpe.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.inpe.model.Preferencia;
import br.inpe.model.Produto;
import br.inpe.model.Usuario;

@Repository
public class PreferenciaRepository extends GenericRepository<Preferencia, Long> {

	@SuppressWarnings("unchecked")
	public List<Preferencia> getPreferecias(Usuario u) {
		Query query = entityManager.createQuery("select distinct p.produto from Preferencia p where p.usuario = :usuario");
		query.setParameter("usuario", u);
		try{
			List<Preferencia> preferencias = new ArrayList<>();
			List<Produto> objetos = query.setMaxResults(5).getResultList();
			for (Produto produto : objetos) {
				preferencias.add(new Preferencia(produto));
			}
			return preferencias;
		}catch(NoResultException e){
			return null;
		}
	}

}
