package com.blackteam.pipboy.data.facade;

import com.blackteam.pipboy.persistence.entity.Person;
import com.blackteam.pipboy.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Implements {@link SampleDataFacade}
 *
 * @author Jiri Oliva
 */
@Component
@Transactional
public class SampleDataFacadeImpl implements SampleDataFacade {

  private static final Logger LOG = LogManager.getLogger(SampleDataFacadeImpl.class);

  @Inject
  private PersonService personService;

  @Override
  public void loadData() {
    createPerson("George", "Woodland", "george20@gmail.com", "password", true);
    createPerson("Lucas", "Oakland", "lucaluca@gmail.com", "password", false);
    createPerson("Anastasia", "Smithy", "anastasia@gmail.com", "password", true);
  }

  private void createPerson(String name, String surname, String email, String password, boolean isAdmin) {
    Person person = new Person();
    person.setName(name);
    person.setSurname(surname);
    person.setEmail(email);
    person.setPassword(password);
    person.setAdministrator(isAdmin);

    personService.registerPerson(person, password);
  }

}
