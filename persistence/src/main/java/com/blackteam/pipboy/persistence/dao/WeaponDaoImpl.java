package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Weapon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of DAO interface for {@link Weapon} entity
 *
 * @author janmichalov
 */
@Repository
public class WeaponDaoImpl implements WeaponDao{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(Weapon weapon) {
        manager.persist(weapon);
    }

    @Override
    public void update(Weapon weapon) {
        manager.merge(weapon);
    }

    @Override
    public void delete(Weapon weapon) {
        manager.remove(manager.merge(weapon));
    }

    @Override
    public List<Weapon> findAll() {
        return manager.createQuery("SELECT w From Weapon w",Weapon.class).getResultList();
    }

    @Override
    public Weapon findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id cannot be null");
        }
        return manager.find(Weapon.class,id);
    }

    @Override
    public List<Weapon> findByName(String name) {
        return manager.createQuery("SELECT w FROM Weapon w where w.name = :name",Weapon.class).setParameter("name",name).getResultList();
    }
}
