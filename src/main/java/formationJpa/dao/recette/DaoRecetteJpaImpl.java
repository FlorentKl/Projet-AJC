package formationJpa.dao.recette;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import formationJpa.context.Context;
import formationJpa.entity.recette.Recette;



public class DaoRecetteJpaImpl implements DaoRecette {

	@Override
	public void insert(Recette obj) {
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
	public Recette update(Recette obj) {
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
	public void delete(Recette obj) {
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
			em.remove(em.find(Recette.class, Key));
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
	public Optional<Recette> findByKey(Integer key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Optional<Recette> optional=Optional.ofNullable(em.find(Recette.class, key));
		if(em!=null && em.isOpen()) {
			em.close();
		}
		
		return optional;
	}

	@Override
	public List<Recette> findAll() {
		List<Recette> Etapes=null;
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Query query=em.createQuery("from Recette p");
		Etapes=query.getResultList();
		if(em!=null && em.isOpen()) {
			em.close();
		}
		return Etapes;
	}
}
