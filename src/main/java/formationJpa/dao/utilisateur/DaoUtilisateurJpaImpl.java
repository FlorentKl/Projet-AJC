package formationJpa.dao.utilisateur;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import formationJpa.context.Context;
import formationJpa.entity.Utilisateur;



public class DaoUtilisateurJpaImpl implements DaoUtilisateur {

	@Override
	public void insert(Utilisateur obj) {
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
	public Utilisateur update(Utilisateur obj) {
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
	public void delete(Utilisateur obj) {
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
			em.remove(em.find(Utilisateur.class, Key));
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
	public Optional<Utilisateur> findByKey(Integer key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Optional<Utilisateur> optional=Optional.ofNullable(em.find(Utilisateur.class, key));
		if(em!=null && em.isOpen()) {
			em.close();
		}
		
		return optional;
	}

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> Etapes=null;
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Query query=em.createQuery("from Utilisateur p");
		Etapes=query.getResultList();
		if(em!=null && em.isOpen()) {
			em.close();
		}
		return Etapes;
	}
}
