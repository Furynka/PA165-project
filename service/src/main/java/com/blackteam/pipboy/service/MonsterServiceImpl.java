package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Monster;

import java.util.List;

/**
 * Monster service implementation
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
public class MonsterServiceImpl implements MonsterService {

    public Monster create(Monster monster) { return null; }

    public Monster update(Monster monster) { return null; }

    public void delete(Monster monster) {}

    public List<Monster> findAll() { return null; }

    public Monster findById(Long id) { return null; }

    public Monster findByName(String name) { return null; }

    public List<Monster> findAllMonstersFromSameArea(Monster monster) { return null; }

    /**
     * Returns the strongest {@link Monster} entity according to its properties.
     * @return {@link Monster} entity
     */
    public static Monster findTheStrongestMonsters() { return null; }
}
