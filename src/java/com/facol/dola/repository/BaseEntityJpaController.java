/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facol.dola.repository;

import com.facol.dola.repository.exceptions.NonexistentEntityException;
import com.facol.dola.models.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dolalima
 */
public class BaseEntityJpaController implements Serializable {

    public BaseEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BaseEntity baseEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(baseEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaseEntity baseEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            baseEntity = em.merge(baseEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = baseEntity.getId();
                if (findBaseEntity(id) == null) {
                    throw new NonexistentEntityException("The baseEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BaseEntity baseEntity;
            try {
                baseEntity = em.getReference(BaseEntity.class, id);
                baseEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baseEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(baseEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaseEntity> findBaseEntityEntities() {
        return findBaseEntityEntities(true, -1, -1);
    }

    public List<BaseEntity> findBaseEntityEntities(int maxResults, int firstResult) {
        return findBaseEntityEntities(false, maxResults, firstResult);
    }

    private List<BaseEntity> findBaseEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaseEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BaseEntity findBaseEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaseEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaseEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaseEntity> rt = cq.from(BaseEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
