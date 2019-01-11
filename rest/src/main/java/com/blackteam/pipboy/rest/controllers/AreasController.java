package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.api.dto.AreaDTO;
import com.blackteam.pipboy.api.facade.AreaFacade;
import javax.inject.Inject;

import com.blackteam.pipboy.rest.exceptions.EntityAlreadyExistsException;
import com.blackteam.pipboy.rest.exceptions.NotFoundException;
import com.blackteam.pipboy.rest.mixin.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jakub Havrila
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI + ApiUris.ROOT_URI_AREAS)
public class AreasController {
    private final Logger logger = LoggerFactory.getLogger(AreasController.class);

    @Inject
    private AreaFacade areaFacade;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean create(@RequestBody AreaDTO areaDTO) {
        logger.debug("creating area");
        try {
            areaFacade.create(areaDTO);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException();
        }
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean update(@PathVariable("id") Long id, @RequestBody AreaDTO areaDTO) {
        logger.debug("update area " + areaDTO);
        areaDTO.setId(id);
        try {
            areaFacade.update(areaDTO);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final boolean deleteArea(@PathVariable("id") Long id) {
        logger.debug("delete area with id "+ id);
        try {
            areaFacade.delete(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AreaDTO getAreaById(@PathVariable("id") Long id) {
        logger.debug("get area with id "+ id);
        try {
            return areaFacade.findById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AreaDTO getAreaByName(@PathVariable("name") String name) {
        logger.debug("get area by name "+ name);
        try {
            return areaFacade.findByName(name);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AreaDTO> getAll() {
        logger.debug("get all areas");
        return areaFacade.findAll();
    }
}
