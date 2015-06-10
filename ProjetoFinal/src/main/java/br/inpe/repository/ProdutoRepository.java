package br.inpe.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.inpe.model.Produto;

@Repository
public class ProdutoRepository extends GenericRepository<Produto, Integer> {

	@SuppressWarnings("unchecked")
	public List<Produto> buscarProdutosComEstoque() {
		Query query = entityManager.createQuery("from Produto p where exists(from Estoque e where p = e.produto and e.quantidade > 0) ");
		try{
			return query.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}

}
