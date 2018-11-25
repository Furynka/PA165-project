package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Monster;
import com.blackteam.pipboy.persistence.entity.Area;

import java.util.List;

/**
 * Monster service interface
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
public interface MonsterService {

    /**
     * Creates {@link Monster} entity.
     * @param monster {@link Monster} entity
     * @return {@link Monster} entity or null.
     */
    Monster create(Monster monster);

    /**
     * Updates {@link Monster} entity.
     * @param monster {@link Monster} entity
     * @return {@link Monster} entity or null.
     */
    Monster update(Monster monster);

    /**
     * Deletes {@link Monster} entity.
     * @param monster {@link Monster} entity
     */
    void delete(Monster monster);

    /**
     * Returns all {@link Monster} entities.
     * @return List of {@link Monster} entities.
     */
    List<Monster> findAll();

    /**
     * Finds {@link Monster} entity by its id.
     * @param id {@link Monster} entity id
     * @return {@link Monster} entity or null.
     */
    Monster findById(Long id);

    /**
     * Finds {@link Monster} entity by its name.
     * @param name {@link Monster} entity name
     * @return {@link Monster} entity or null.
     */
    Monster findByName(String name);

    /**
     * Returns all {@link Monster} entities from same {@link Area}.
     * @return List of {@link Monster} entities.
     */
    List<Monster> findAllMonstersFromSameArea(Monster monster);
}
