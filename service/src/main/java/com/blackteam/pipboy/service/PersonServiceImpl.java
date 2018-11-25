package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.PersonDao;
import com.blackteam.pipboy.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation class of {@link PersonService}.
 *
 * @author Jiri Oliva
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonDao personDao;

  @Override
  public Person registerPerson(Person person, String password) {
    person.setPassword(password);
    personDao.create(person);
    //TODO - return an instance?
    return null;
  }

  @Override
  public boolean authenticate(Person person, String password) {
    return person.getPassword().equals(password);
  }

  @Override
  public void changePassword(Person person, String newPassword) {
    person.setPassword(newPassword);
    personDao.update(person);
  }

  @Override
  public void changeRights(Person person, Boolean isAdmin) {
    person.setAdministrator(isAdmin);
    personDao.update(person);
  }

  @Override
  public Person findPersonById(Long id) {
    return personDao.findById(id);
  }

  @Override
  public Person findPersonByEmail(String email) {
    return personDao.findByEmail(email);
  }

  @Override
  public List<Person> findAll() {
    return personDao.findAll();
  }
}