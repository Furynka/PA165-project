package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Person;

import java.util.List;

/**
 * Access interface to persistence for {@link Person} entities.
 *
 * @author Jiri Oliva
 */
public interface PersonDao {
  /**
   * Creates new {@link Person} entity if not already existing in persistence.
   *
   * @param person
   */
  void create(Person person);

  /**
   * Deletes {@link Person} entity if existing in persistence.
   *
   * @param person
   */
  void delete(Person person);

  /**
   * Updates {@link Person} entity.
   *
   * @param person
   */
  void update(Person person);

  /**
   * Finds {@link Person} entity by given id.
   *
   * @param id
   * @return Person class or null if not found
   */
  Person findById(Long id);

  /**
   * Finds {@link Person} entity by given email.
   *
   * @param email
   * @return Person class or null if not found
   */
  Person findByEmail(String email);

  /**
   * Finds list of {@link Person} entities with given name.
   *
   * @param name
   * @return list (can be empty)
   */
  List<Person> findByName(String name);

  /**
   * Finds list of {@link Person} entities with given surname.
   *
   * @param surname
   * @return list (can be empty)
   */
  List<Person> findBySurname(String surname);

  /**
   * Returns all existing {@link Person} entities.
   *
   * @return list (can be empty)
   */
  List<Person> findAll();
}
