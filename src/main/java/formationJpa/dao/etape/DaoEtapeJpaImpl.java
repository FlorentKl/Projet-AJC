package formationJpa.dao.etape;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import formationJpa.context.Context;
import formationJpa.entity.recette.EtapeRecette;



public class DaoEtapeJpaImpl implements DaoEtape {

	@Override
	public void insert(EtapeRecette obj) {
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
	public EtapeRecette update(EtapeRecette obj) {
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
	public void delete(EtapeRecette obj) {
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
			em.remove(em.find(EtapeRecette.class, Key));
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
	public Optional<EtapeRecette> findByKey(Integer key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Optional<EtapeRecette> optional=Optional.ofNullable(em.find(EtapeRecette.class, key));
		if(em!=null && em.isOpen()) {
			em.close();
		}
		
		return optional;
	}

	@Override
	public List<EtapeRecette> findAll() {
		List<EtapeRecette> Etapes=null;
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Query query=em.createQuery("from EtapeRecette p");
		Etapes=query.getResultList();
		if(em!=null && em.isOpen()) {
			em.close();
		}
		return Etapes;
	}
}
