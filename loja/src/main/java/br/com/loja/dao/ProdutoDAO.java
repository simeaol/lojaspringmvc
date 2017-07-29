package br.com.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void save(Produto produto) {
		entityManager.persist(produto);
	}

	public List<Produto> findAll() {
		return entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		TypedQuery<Produto> query = entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos preco where p.id = :id ", Produto.class);
		query.setParameter("id", id);
		return query.getSingleResult();		
		
	}
}
