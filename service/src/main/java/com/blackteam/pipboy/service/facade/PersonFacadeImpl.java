package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.PersonDTO;
import com.blackteam.pipboy.api.dto.PersonLoginDTO;
import com.blackteam.pipboy.api.facade.PersonFacade;
import com.blackteam.pipboy.persistence.entity.Person;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation class for {@link PersonFacade}.
 *
 * @author Jiri Oliva
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

  @Autowired
  private PersonService personService;

  @Autowired
  private BeanMappingService beanMappingService;

  @Override
  public PersonDTO registerPerson(PersonDTO person, String password) {
    Person personEntity = beanMappingService.mapTo(person, Person.class);
    personService.registerPerson(personEntity, password);

    //TODO - return an instance?
    return null;
  }

  @Override
  public boolean authenticate(PersonLoginDTO credentials) {
    Person person = personService.findPersonByEmail(credentials.getEmail());
    return personService.authenticate(person, credentials.getPassword());
  }

  public boolean isAdmin(PersonDTO person) {
    Person personEntity = beanMappingService.mapTo(person, Person.class);
    return personService.isAdmin(personEntity);
  }

  @Override
  public void changePassword(PersonLoginDTO credentials) {
    Person person = personService.findPersonByEmail(credentials.getEmail());
    personService.changePassword(person, credentials.getNewPassword());
  }

  @Override
  public void changeRights(PersonDTO person, Boolean isAdmin) {
    Person personEntity = beanMappingService.mapTo(person, Person.class);
    personService.changeRights(personEntity, isAdmin);
  }

  @Override
  public PersonDTO findPersonById(Long id) {
    Person person = personService.findPersonById(id);
    return beanMappingService.mapTo(person, PersonDTO.class);
  }

  @Override
  public PersonDTO findPersonByEmail(String email) {
    Person person = personService.findPersonByEmail(email);
    return beanMappingService.mapTo(person, PersonDTO.class);
  }

  @Override
  public List<PersonDTO> findAllPersons() {
    List<Person> persons = personService.findAll();
    return beanMappingService.mapTo(persons, PersonDTO.class);
  }
}
