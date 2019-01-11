package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.api.dto.MonsterCreateDTO;
import com.blackteam.pipboy.api.dto.MonsterUpdateDTO;
import com.blackteam.pipboy.api.facade.MonsterFacade;
import com.blackteam.pipboy.api.dto.MonsterDTO;
import com.blackteam.pipboy.rest.mixin.ApiUris;
import com.blackteam.pipboy.rest.exceptions.EntityAlreadyExistsException;
import com.blackteam.pipboy.rest.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.List;

/**
 * Monsters controller
 *
 * @author  Jiří Čechák
 * @since 2018-12-12
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI + ApiUris.ROOT_URI_MONSTERS)
public class MonstersController {

    final static Logger logger = LoggerFactory.getLogger(MonstersController.class);

    @Inject
    private MonsterFacade monsterFacade;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO createMonster(@RequestBody MonsterCreateDTO monster) throws Exception {
        try {
            return monsterFacade.findById(monsterFacade.create(monster));
        } catch (Exception ex) {
            throw new EntityAlreadyExistsException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void update(@RequestBody MonsterUpdateDTO monster) throws Exception {
        try {
            monsterFacade.update(monster);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteMonster(@PathVariable("id") long id) throws Exception {
        try {
            MonsterDTO monster = new MonsterDTO();
            monster.setId(id);
            monsterFacade.delete(monster);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MonsterDTO> findAllMonsters() {
        return monsterFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO findMonsterById(@PathVariable("id") Long id) throws Exception {
        try {
            return monsterFacade.findById(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }

    }

    @RequestMapping(value = "/monstersFromSameArea",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MonsterDTO> findAllMonstersFromSameArea(@RequestBody MonsterDTO monster) throws Exception {
        try {
            return monsterFacade.findAllMonstersFromSameArea(monster);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/strongestMonster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO findStrongestMonster() {
        MonsterDTO monster = monsterFacade.findTheStrongestMonster();

        if (monster == null) {
            throw new NotFoundException();
        }
        return monster;
    }

}