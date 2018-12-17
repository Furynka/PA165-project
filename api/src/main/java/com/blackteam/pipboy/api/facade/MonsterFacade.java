package com.blackteam.pipboy.api.facade;

import com.blackteam.pipboy.api.dto.MonsterCreateDTO;
import com.blackteam.pipboy.api.dto.MonsterDTO;
import com.blackteam.pipboy.api.dto.MonsterUpdateDTO;
import com.blackteam.pipboy.persistence.entity.Monster;

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
     * @param monster {@link MonsterCreateDTO}
     * @return monster id
     */
    Long create(MonsterCreateDTO monster);

    /**
     * Updates Monster.
     * @param monster {@link MonsterDTO}
     */
    void update(MonsterUpdateDTO monster);

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

    /**
     * Returns all {@link MonsterDTO} from same {@link com.blackteam.pipboy.api.dto.AreaDTO}.
     * @param monster {@link MonsterDTO}
     * @return List of {@link MonsterDTO}.
     */
    List<MonsterDTO> findAllMonstersFromSameArea(MonsterDTO monster);

    /**
     * Finds the strongest {@link MonsterDTO} according to its properties.
     * @return {@link MonsterDTO} or null.
     */
    MonsterDTO findTheStrongestMonster();
}
