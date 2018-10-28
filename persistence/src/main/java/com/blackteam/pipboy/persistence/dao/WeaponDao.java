package com.blackteam.pipboy.persistence.dao;
import com.blackteam.pipboy.persistence.entity.Weapon;

import java.util.List;

/**
 * Data Access interface for entity {@link Weapon}
 *
 * @author janmichalov
 */
public interface WeaponDao {
    /**
     * Creates and persists Weapon entity.
     * @param weapon Weapon entity
     */
    void create(Weapon weapon);

    /**
     * Updates Weapon entity.
     * @param weapon Weapon entity
     */
    void update(Weapon weapon);

    /**
     * Deletes Weapon entity.
     * @param weapon Weapon entity
     */
    void delete(Weapon weapon);

    /**
     * Returns all Weapon entities.
     * @return List of Weapon entities.
     */
    List<Weapon> findAll();

    /**
     * Finds Weapon entity by its id.
     * @param id Weapon entity id
     * @return Weapon entity or null.
     */
    Weapon findById(Long id);

    /**
     * Finds Weapons entities by its name.
     * @param name Weapon entity name
     * @return Weapon entity or null.
     */
    List<Weapon> findByName(String name);
}
