package br.inpe.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.inpe.model.Movie;

@Repository
public class MovieRepository extends br.inpe.repository.GenericRepository<Movie, Integer>{
	
	@SuppressWarnings("unchecked")
	public List<Movie> findByTitle(String title){
		Query query = entityManager.createQuery("from Movie m where m.title like :title");
		 query.setParameter("title", "%" + title + "%");
		 return query.getResultList();
	}
	
	
	

}
