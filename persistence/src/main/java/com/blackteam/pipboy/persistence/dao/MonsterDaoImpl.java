package com.blackteam.pipboy.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.blackteam.pipboy.persistence.entity.Monster;

/**
 * Dao class for Monster entity.
 *
 * @author  Jiří Čechák
 * @since   2018-10-26
 */
@Repository
public class MonsterDaoImpl implements MonsterDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Monster monster) {
        em.persist(monster);
    }

    @Override
    public void update(Monster monster) {
        em.merge(monster);
    }

    @Override
    public void delete(Monster monster) {
        em.remove(monster);
    }

    @Override
    public List<Monster> findAll() {
        return em.createQuery("select m from Monster m", Monster.class)
                .getResultList();
    }

    @Override
    public Monster findById(Long id) {
        return em.find(Monster.class, id);
    }

    @Override
    public Monster findByName(String name) {
        try {
            return em.createQuery("select m from Monster m where m.name = :name", Monster.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
