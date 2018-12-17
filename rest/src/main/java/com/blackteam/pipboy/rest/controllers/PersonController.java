package com.blackteam.pipboy.rest.controllers;

import com.blackteam.pipboy.api.dto.*;
import com.blackteam.pipboy.api.facade.PersonFacade;
import com.blackteam.pipboy.rest.mixin.ApiUris;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI + ApiUris.ROOT_URI_PERSONS)
public class PersonController {

  private static final Logger LOG = LogManager.getLogger(PersonController.class);

  @Inject
  private PersonFacade personFacade;


  @RequestMapping(value="/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public final void register(@RequestBody RegisterPersonDTO personDTO) throws JsonProcessingException {
    LOG.debug("registerPerson requested");

    personFacade.registerPerson(personDTO);
  }

  @RequestMapping(value="/authenticate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public final boolean authenticate(@RequestBody PersonLoginDTO loginDTO) throws JsonProcessingException {
    LOG.debug("authenticatePerson requested");

    return personFacade.authenticate(loginDTO);
  }

  @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public final boolean delete(@PathVariable long id) throws JsonProcessingException {
    LOG.debug("delete person requested");

    personFacade.deletePerson(id);
    return true;
  }

  @RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public final List<PersonDTO> getAll() throws JsonProcessingException {
    LOG.debug("find all requested");

    return personFacade.findAllPersons();
  }

  @RequestMapping(value="/findPerson/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public final PersonDTO findById(@PathVariable long id) throws JsonProcessingException {
    LOG.debug("find person by id requested");

    return personFacade.findPersonById(id);
  }

  @RequestMapping(value="/findPerson/email/", method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public final PersonDTO findByEmail(@RequestParam(name = "encodedEmail") String encodedEmail) throws JsonProcessingException {
    LOG.debug("find person by email requested");
    try {
      String email = URLDecoder.decode(encodedEmail, "UTF-8");
      return personFacade.findPersonByEmail(email);
    } catch (UnsupportedEncodingException e) {
      LOG.error("wrong encoding");
      return null;
    }
  }

  @RequestMapping(value="/changePassword", method = RequestMethod.POST)
  public final void changePassword(@RequestBody PersonChangePasswordDTO passwordDTO) throws JsonProcessingException {
    LOG.debug("change password requested");

    personFacade.changePassword(passwordDTO);
  }

  @RequestMapping(value="/update", method = RequestMethod.PUT)
  public final void update(@RequestBody PersonUpdateDTO updateDTO) throws JsonProcessingException {
    LOG.debug("update person requested");

    personFacade.update(updateDTO);
  }

}
