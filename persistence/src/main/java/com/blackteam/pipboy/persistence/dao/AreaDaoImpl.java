package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Area;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implement AreaDao
 *
 * @author Jakub Havrila
 */
@Repository
public class AreaDaoImpl implements AreaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Area findById(Long id){
        return em.find(Area.class, id);
    }

    @Override
    public void create(Area a) {
        em.persist(a);
    }

    @Override
    public void update(Area a) {
        em.merge(a);
    }

    @Override
    public void delete(Area a) {
        em.remove(em.merge(a));
    }

    @Override
    public List<Area> findAll() {
        return em.createQuery("select a from Area a", Area.class).getResultList();
    }

    @Override
    public Area findByName(String name) {
        try {
            return em.createQuery("select a from Area a where a.name = :name", Area.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
