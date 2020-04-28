package formationJpa.dao.associationRecetteCommentaire;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import formationJpa.context.Context;
import formationJpa.entity.AssociationRecetteCommentaire;
import formationJpa.entity.AssociationRecetteCommentaireKey;


public class DaoAssociationRecetteCommentaireJpaImpl implements DaoAssociationRecetteCommentaire {

	@Override
	public void insert(AssociationRecetteCommentaire obj) {
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
	public AssociationRecetteCommentaire update(AssociationRecetteCommentaire obj) {
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
	public void delete(AssociationRecetteCommentaire obj) {
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
	public void deleteByKey(AssociationRecetteCommentaireKey Key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(em.find(AssociationRecetteCommentaire.class, Key));
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
	public Optional<AssociationRecetteCommentaire> findByKey(AssociationRecetteCommentaireKey key) {
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Optional<AssociationRecetteCommentaire> optional=Optional.ofNullable(em.find(AssociationRecetteCommentaire.class, key));
		if(em!=null && em.isOpen()) {
			em.close();
		}
		
		return optional;
	}

	@Override
	public List<AssociationRecetteCommentaire> findAll() {
		List<AssociationRecetteCommentaire> AssociationRecetteCommentaires=null;
		EntityManager em=Context.getEntityManagerFactory().createEntityManager();
		Query query=em.createQuery("from AssociationRecetteCommentaire p");
		AssociationRecetteCommentaires=query.getResultList();
		if(em!=null && em.isOpen()) {
			em.close();
		}
		return AssociationRecetteCommentaires;
	}
}
