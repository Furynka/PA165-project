package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.api.dto.AreaDTO;
import com.blackteam.pipboy.api.facade.AreaFacade;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jakub Havrila
 */
@RestController
@RequestMapping("/pa165/rest/areas")
public class AreasController {
    private final Logger logger = LoggerFactory.getLogger(AreasController.class);

    @Inject
    private AreaFacade areaFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean create(@RequestBody AreaDTO areaDTO) {
        logger.debug("creating area");
        areaFacade.create(areaDTO);
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean update(@PathVariable("id") Long id, @RequestBody AreaDTO areaDTO) {
        logger.debug("update area " + areaDTO);
        areaFacade.update(areaDTO);
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final boolean deleteArea(@PathVariable("id") Long id) {
        logger.debug("delete area with id "+ id);
        areaFacade.delete(id);
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AreaDTO getAreaById(@PathVariable("id") Long id) {
        logger.debug("get area with id "+ id);
        return areaFacade.findById(id);
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AreaDTO getAreaByName(@PathVariable("name") String name) {
        logger.debug("get area by name "+ name);
        return areaFacade.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AreaDTO> getAll() {
        logger.debug("get all areas");
        return areaFacade.findAll();
    }
}
