package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Access class for {@link Person} persistence.
 *
 * @author Jiri Oliva
 */
@Repository
public class PersonDaoImpl implements PersonDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void create(Person person) {
    entityManager.persist(person);
  }

  @Override
  public void delete(Person person) {
    entityManager.remove(findById(person.getId()));
  }

  @Override
  public void update(Person person) {
    entityManager.merge(person);
  }

  @Override
  public Person findById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id can not be null");
    }

    return entityManager.find(Person.class, id);
  }

  @Override
  public Person findByEmail(String email) {
    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email is invalid");
    }

    return entityManager.createQuery("select p from Person where p.email=:email", Person.class)
            .setParameter("email", email).getSingleResult();
  }

  @Override
  public List<Person> findByName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is invalid");
    }

    return entityManager.createQuery("select p from Person where p.name=:name", Person.class)
            .setParameter("name", name).getResultList();
  }

  @Override
  public List<Person> findBySurname(String surname) {
    if (surname == null || surname.isEmpty()) {
      throw new IllegalArgumentException("Surname is invalid");
    }

    return entityManager.createQuery("select p from Person where p.surname=:surname", Person.class)
            .setParameter("surname", surname).getResultList();
  }

  @Override
  public List<Person> findAll() {
    return entityManager.createQuery("select p from Person", Person.class).getResultList();
  }
}
