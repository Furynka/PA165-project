package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.api.dto.PersonDTO;
import com.blackteam.pipboy.api.dto.PersonLoginDTO;
import com.blackteam.pipboy.api.dto.RegisterPersonDTO;
import com.blackteam.pipboy.api.facade.PersonFacade;
import com.blackteam.pipboy.rest.mixin.ApiUris;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

//@CrossOrigin(origins = "localhost:3000")
@RestController
@RequestMapping(ApiUris.ROOT_URI + ApiUris.ROOT_URI_PERSONS)
public class PersonController {

  private static final Logger LOG = LogManager.getLogger(PersonController.class);

  @Inject
  private PersonFacade personFacade;

  @RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public final List<PersonDTO> getAll() throws JsonProcessingException {
    LOG.debug("getAll requested");

    return personFacade.findAllPersons();
  }

  @RequestMapping(value="/findPerson/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public final PersonDTO findPersonById(@PathVariable long id) throws JsonProcessingException {
    LOG.debug("findPersonById requested");

    return personFacade.findPersonById(id);
  }

  @RequestMapping(value="/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public final void register(@RequestBody RegisterPersonDTO personDTO) throws JsonProcessingException {
    LOG.debug("registerPerson requested");

    personFacade.registerPerson(personDTO);
  }

  @RequestMapping(value="/authenticate", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public final boolean authenticate(@RequestBody PersonLoginDTO loginDTO) throws JsonProcessingException {
    LOG.debug("authenticatePerson requested");

    return personFacade.authenticate(loginDTO);
  }

  @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public final boolean delete(@PathVariable long id) throws JsonProcessingException {
    LOG.debug("delete person requested with id: " + id);

    personFacade.deletePerson(id);
    return true;
  }

  @RequestMapping(value="/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public final PersonDTO findById(@PathVariable long id) throws JsonProcessingException {
    LOG.debug("find person requested with id: " + id);

    return personFacade.findPersonById(id);
  }

//  @RequestMapping(value="/findByEmail", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
//          produces = MediaType.APPLICATION_JSON_VALUE)
//  public final PersonDTO findById(@RequestBody long id) throws JsonProcessingException {
//    LOG.debug("find person requested with id: " + id);
//
//    return personFacade.findPersonById(id);
//  }

}
