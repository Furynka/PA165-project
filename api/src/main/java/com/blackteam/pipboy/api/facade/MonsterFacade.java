package com.blackteam.pipboy.api.facade;

import com.blackteam.pipboy.api.dto.MonsterDTO;

import java.util.List;

/**
 * Monster facade interface
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
public interface MonsterFacade {

    /**
     * Creates new Monster.
     * @param monster {@link MonsterDTO}
     * @return monster id
     */
    Long create(MonsterDTO monster);

    /**
     * Updates Monster.
     * @param monster {@link MonsterDTO}
     */
    void update(MonsterDTO monster);

    /**
     * Deletes Monster.
     * @param monster {@link MonsterDTO}
     */
    void delete(MonsterDTO monster);

    /**
     * Returns all Monsters.
     * @return List of Monsters.
     */
    List<MonsterDTO> findAll();

    /**
     * Finds Monster by its id.
     * @param id Monster id
     * @return {@link MonsterDTO} or null.
     */
    MonsterDTO findById(Long id);

    /**
     * Finds Monster by its name.
     * @param name Monster name
     * @return {@link MonsterDTO} or null.
     */
    MonsterDTO findByName(String name);
}
