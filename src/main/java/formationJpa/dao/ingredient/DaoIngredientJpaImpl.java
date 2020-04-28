package formationJpa.dao.ingredient;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import formationJpa.context.Context;
import formationJpa.dao.etape.DaoEtape;
import formationJpa.entity.Ingredients.Ingredient;



public class DaoIngredientJpaImpl implements DaoIngredient {

	@Override
	public void insert(Ingredient obj) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(obj);;
			tx.commit();
		}catch(Exception e) {
			if(tx !=null && tx.isActive()) {
				tx.rollback();
			e.printStackTrace();
			}
		}
		if(em!=null && em.isOpen()) {
		em.close();
		}
	}

	@Override
	public Ingredient update(Ingredient obj) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			obj=em.merge(obj);
			em.persist(obj);
			tx.commit();
		}catch(Exception e) {
			if(tx !=null && tx.isActive()) {
				tx.rollback();
			e.printStackTrace();
			}
		}
		if(em!=null && em.isOpen()) {
		em.close();
		}
		return obj;
	}

	@Override
	public void delete(Ingredient obj) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(em.merge(obj));
			tx.commit();
		}catch(Exception e) {
			if(tx !=null && tx.isActive()) {
				tx.rollback();
			e.printStackTrace();
			}
		}
		if(em!=null && em.isOpen()) {
		em.close();
		}
		
	}

	@Override
	public void deleteByKey(Integer Key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(em.find(Ingredient.class, Key));
			tx.commit();
		}catch(Exception e) {
			if(tx !=null && tx.isActive()) {
				tx.rollback();
			e.printStackTrace();
			}
		}
		if(em!=null && em.isOpen()) {
		em.close();
		}
	}

	@Override
	public Optional<Ingredient> findByKey(Integer key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Optional<Ingredient> optional=Optional.ofNullable(em.find(Ingredient.class, key));
		if(em!=null && em.isOpen()) {
			em.close();
		}
		
		return optional;
	}

	@Override
	public List<Ingredient> findAll() {
		List<Ingredient> Etapes=null;
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Query query=em.createQuery("from Ingredient p");
		Etapes=query.getResultList();
		if(em!=null && em.isOpen()) {
			em.close();
		}
		return Etapes;
	}
}
