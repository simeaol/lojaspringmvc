package br.com.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	public void save(Produto produto){
		entityManager.persist(produto);
	}
}
