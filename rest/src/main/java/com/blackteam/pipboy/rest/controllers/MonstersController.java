package com.blackteam.pipboy.rest.controllers;

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
@RequestMapping(ApiUris.ROOT_URI_MONSTERS)
public class MonstersController {

    final static Logger logger = LoggerFactory.getLogger(MonstersController.class);

    @Inject
    private MonsterFacade monsterFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO createMonster(@RequestBody MonsterDTO monster) throws Exception {
        logger.debug("createMonster()");
        try {
            return monsterFacade.findById(monsterFacade.create(monster));
        } catch (Exception ex) {
            throw new EntityAlreadyExistsException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteMonster(@PathVariable("id") long id) throws Exception {
        logger.debug("deleteMonster({})", id);
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
        logger.debug("findAllMonsters()");
        return monsterFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO findMonsterById(@PathVariable("id") long id) throws Exception {
        logger.debug("findMonsterById({})", id);
        try {
            return monsterFacade.findById(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }

    }
}