package formationJpa.dao.associationIngredientRecette;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import formationJpa.context.Context;
import formationJpa.entity.Ingredients.AssociationIngredientRecette;
import formationJpa.entity.Ingredients.AssociationIngredientRecetteKey;


public class DaoAssociationIngredientRecetteJpaImpl implements DaoAssociationIngredientRecette {

	@Override
	public void insert(AssociationIngredientRecette obj) {
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
	public AssociationIngredientRecette update(AssociationIngredientRecette obj) {
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
	public void delete(AssociationIngredientRecette obj) {
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
	public void deleteByKey(AssociationIngredientRecetteKey Key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(em.find(AssociationIngredientRecette.class, Key));
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
	public Optional<AssociationIngredientRecette> findByKey(AssociationIngredientRecetteKey key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Optional<AssociationIngredientRecette> optional=Optional.ofNullable(em.find(AssociationIngredientRecette.class, key));
		if(em!=null && em.isOpen()) {
			em.close();
		}
		
		return optional;
	}

	@Override
	public List<AssociationIngredientRecette> findAll() {
		List<AssociationIngredientRecette> AssociationIngredientRecettes=null;
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Query query=em.createQuery("from AssociationIngredientRecette p");
		AssociationIngredientRecettes=query.getResultList();
		if(em!=null && em.isOpen()) {
			em.close();
		}
		return AssociationIngredientRecettes;
	}
}
