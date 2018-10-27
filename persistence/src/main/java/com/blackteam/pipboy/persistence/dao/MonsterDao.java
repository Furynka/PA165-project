package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Monster;

import java.util.List;

/**
 * Dao interface for Monster entity.
 *
 * @author  Jiří Čechák
 * @since 2018-10-26
 */
interface MonsterDao {

    /**
     * Creates Monster entity.
     * @param monster Monster entity
     */
    void create(Monster monster);

    /**
     * Updates Monster entity.
     * @param monster Monster entity
     */
    void update(Monster monster);

    /**
     * Deletes Monster entity.
     * @param monster Monster entity
     */
    void delete(Monster monster);

    /**
     * Returns all Monster entities.
     * @return List of Monster entities.
     */
    List<Monster> findAll();

    /**
     * Finds Monster entity by its id.
     * @param id Monster entity id
     * @return Monster entity or null.
     */
    Monster findById(Long id);

    /**
     * Finds Monster entity by its name.
     * @param name Monster entity name
     * @return Monster entity or null.
     */
    Monster findByName(String name);
}

