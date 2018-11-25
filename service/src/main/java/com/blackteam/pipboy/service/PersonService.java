package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Person;

import java.util.List;

/**
 * Service layer for {@link Person}.
 *
 * @author Jiri Oliva
 */
public interface PersonService {

  /**
   * Register new {@link Person}.
   *
   * @param person
   * @param password
   * @return {@link Person} if successfully registered
   */
  Person registerPerson(Person person, String password);

  /**
   * Authenticate given {@link Person} with given password.
   *
   * @param person
   * @param password
   * @return true if successfully authenticated
   */
  boolean authenticate(Person person, String password);

  /**
   * Change password of Person.
   *
   * @param person
   * @param newPassword
   */
  void changePassword(Person person, String newPassword);

  /**
   * Change rights of Person.
   *
   * @param person
   * @param isAdmin
   */
  void changeRights(Person person, Boolean isAdmin);

  /**
   * Find Person by given id.
   *
   * @param id
   * @return {@link Person} if successfully found
   */
  Person findPersonById(Long id);

  /**
   * Find Person by given email.
   *
   * @param email
   * @return {@link Person} if successfully found
   */
  Person findPersonByEmail(String email);

  /**
   * Returns all existing Persons.
   *
   * @return list of existing Persons.
   */
  List<Person> findAll();
}