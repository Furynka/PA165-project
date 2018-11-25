package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Person;

import java.util.List;

/**
 * Implementation class of {@link PersonService}.
 *
 * @author Jiri Oliva
 */
public class PersonServiceImpl implements PersonService {
  @Override
  public Person registerPerson(Person person, String password) {
    return null;
  }

  @Override
  public boolean authenticate(Person person) {
    return false;
  }

  @Override
  public void changePassword(Person person) {

  }

  @Override
  public void changeRights(Person person, Boolean isAdmin) {

  }

  @Override
  public Person findPersonById(Long id) {
    return null;
  }

  @Override
  public Person findPersonByEmail(String email) {
    return null;
  }

  @Override
  public List<Person> findAllPersons() {
    return null;
  }
}
