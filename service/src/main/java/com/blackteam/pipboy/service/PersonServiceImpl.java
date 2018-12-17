package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.PersonDao;
import com.blackteam.pipboy.persistence.entity.Person;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation class of {@link PersonService}.
 *
 * @author Jiri Oliva
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Inject
  private PersonDao personDao;

  @Override
  public void registerPerson(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("Person is null");
    }

    person.setPassword(BCrypt.hashpw(person.getPassword(), BCrypt.gensalt()));
    personDao.create(person);
  }

  @Override
  public void deletePerson(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("Person is null");
    }

    personDao.delete(person);
  }

  @Override
  public boolean authenticate(Person person, String password) {
    if (person == null) {
      throw new IllegalArgumentException("Person is null");
    } else if (password == null) {
      throw new IllegalArgumentException("Password is null");
    }

    return BCrypt.checkpw(password, person.getPassword());
  }

  @Override
  public boolean isAdmin(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("Person is null");
    }
    return person.getAdministrator();
  }

  @Override
  public void changePassword(Person person, String newPassword) {
    if (person == null) {
      throw new IllegalArgumentException("Person is null");
    } else if (newPassword == null) {
      throw new IllegalArgumentException("New password in null");
    }

    person.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    personDao.update(person);
  }

  @Override
  public void changeRights(Person person, Boolean isAdmin) {
    if (person == null) {
       throw new IllegalArgumentException("Person is null");
    } else if (isAdmin == null) {
      throw new IllegalArgumentException("isAdmin argument is null");
    }

    person.setAdministrator(isAdmin);
    personDao.update(person);
  }

  @Override
  public void update(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("Person is null");
    }

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
