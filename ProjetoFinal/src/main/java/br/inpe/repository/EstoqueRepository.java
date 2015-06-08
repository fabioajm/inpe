package br.inpe.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.inpe.model.Estoque;
import br.inpe.model.Produto;

@Repository
public class EstoqueRepository extends
		br.inpe.repository.GenericRepository<Estoque, Long> {

	public Estoque getEstoque(Produto p) {
		try{
			Query query = entityManager.createQuery("from Estoque e where e.produto = :produto");
			query.setParameter("produto", p);
			return (Estoque) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

}
