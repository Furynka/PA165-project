package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.*;
import com.blackteam.pipboy.api.facade.PersonFacade;
import com.blackteam.pipboy.persistence.entity.Person;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation class for {@link PersonFacade}.
 *
 * @author Jiri Oliva
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

  @Inject
  private PersonService personService;

  @Autowired
  private BeanMappingService beanMappingService;


  @Override
  public void registerPerson(RegisterPersonDTO person) {
    Person personEntity = beanMappingService.mapTo(person, Person.class);
    personEntity.setAdministrator(false);
    personService.registerPerson(personEntity);
  }

  @Override
  public void deletePerson(Long id) {
    personService.deletePerson(personService.findPersonById(id));
  }

  @Override
  public boolean authenticate(PersonLoginDTO credentials) {
    try {
      Person person = personService.findPersonByEmail(credentials.getEmail());
      return personService.authenticate(person, credentials.getPassword());
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public boolean isAdmin(PersonDTO person) {
    Person personEntity = beanMappingService.mapTo(person, Person.class);
    return personService.isAdmin(personEntity);
  }

  @Override
  public void changePassword(PersonChangePasswordDTO changePasswordDTO) {
    Person person = personService.findPersonById(changePasswordDTO.getId());
    personService.changePassword(person, changePasswordDTO.getPassword());
  }

  @Override
  public void changeRights(PersonDTO person, Boolean isAdmin) {
    Person personEntity = beanMappingService.mapTo(person, Person.class);
    personService.changeRights(personEntity, isAdmin);
  }

  @Override
  public void update(PersonUpdateDTO updateDTO) {
    Person personEntity = beanMappingService.mapTo(updateDTO, Person.class);
    personEntity.setPassword(findPersonById(personEntity.getId()).getPassword());
    personService.update(personEntity);
  }

  @Override
  public PersonDTO findPersonById(Long id) {
    Person person = personService.findPersonById(id);
    return person == null ? null : beanMappingService.mapTo(person, PersonDTO.class);
  }

  @Override
  public PersonDTO findPersonByEmail(String email) {
    Person person = personService.findPersonByEmail(email);
    return person == null ? null : beanMappingService.mapTo(person, PersonDTO.class);
  }

  @Override
  public List<PersonDTO> findAllPersons() {
    List<Person> persons = personService.findAll();
    return beanMappingService.mapTo(persons, PersonDTO.class);
  }
}
