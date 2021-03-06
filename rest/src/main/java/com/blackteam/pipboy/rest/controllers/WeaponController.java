package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.api.dto.WeaponCreateDTO;
import com.blackteam.pipboy.api.dto.WeaponDTO;
import com.blackteam.pipboy.api.facade.WeaponFacade;
import com.blackteam.pipboy.rest.exceptions.EntityAlreadyExistsException;
import com.blackteam.pipboy.rest.exceptions.NotFoundException;
import com.blackteam.pipboy.rest.mixin.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jan Michalov
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI + ApiUris.ROOT_URI_WEAPONS)
public class WeaponController {
    private final Logger logger = LoggerFactory.getLogger(WeaponController.class);

    @Inject
    private WeaponFacade weaponFacade;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long create(@RequestBody WeaponCreateDTO weapon) {
        logger.debug("weapons/create requested with: " + weapon);

        try {
            return weaponFacade.create(weapon);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<WeaponDTO> getAll() {
        logger.debug("weapons/getAll requested");
        return weaponFacade.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WeaponDTO getSpecific(@PathVariable("id") Long id) {
        logger.debug("weapons/{id} requested with id: " + id);
        try {
            return weaponFacade.findById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final boolean delete(@PathVariable("id") Long id) {
        logger.debug("weapons/{id} deleted with id: " + id);
        try {
            weaponFacade.delete(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean update(@PathVariable("id") Long id ,@RequestBody WeaponDTO weapon) {
        logger.debug("weapons/ requested update with weapon: " + weapon);
        weapon.setId(id);

        try {
            weaponFacade.update(weapon);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public final WeaponDTO findByName(@PathVariable("name") String name){
        logger.debug("weapons/name/{name} requested with name: " + name);
        try {
            return weaponFacade.findByName(name);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
