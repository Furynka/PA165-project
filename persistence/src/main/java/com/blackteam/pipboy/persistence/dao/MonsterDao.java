package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Monster;

import java.util.List;

/**
 * Dao interface for Monster entity.
 *
 * @author  Jiří Čechák
 * @since 2018-10-26
 */
public interface MonsterDao {

    /**
     * Creates {@link Monster} entity.
     * @param monster {@link Monster} entity
     */
    public void create(Monster monster);

    /**
     * Updates {@link Monster} entity.
     * @param monster {@link Monster} entity
     */
    public void update(Monster monster);

    /**
     * Deletes {@link Monster} entity.
     * @param monster {@link Monster} entity
     */
    public void delete(Monster monster);

    /**
     * Returns all {@link Monster} entities.
     * @return List of {@link Monster} entities.
     */
    public List<Monster> findAll();

    /**
     * Finds {@link Monster} entity by its id.
     * @param id {@link Monster} entity id
     * @return {@link Monster} entity or null.
     */
    public Monster findById(Long id);

    /**
     * Finds {@link Monster} entity by its name.
     * @param name {@link Monster} entity name
     * @return {@link Monster} entity or null.
     */
    public Monster findByName(String name);
}

