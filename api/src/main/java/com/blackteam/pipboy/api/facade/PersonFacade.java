package com.blackteam.pipboy.api.facade;

import com.blackteam.pipboy.api.dto.PersonDTO;
import com.blackteam.pipboy.api.dto.PersonLoginDTO;

import java.util.List;

/**
 * Facade interface for Person.
 *
 * @author Jiri Oliva
 */
public interface PersonFacade {

  /**
   * Registers new Person.
   *
   * @param person
   * @param password
   * @return {@link PersonDTO} if successfully registered
   */
  PersonDTO registerPerson(PersonDTO person, String password);

  /**
   * Authenticate given login credentials.
   *
   * @param credentials
   * @return true if successfully authenticated
   */
  boolean authenticate(PersonLoginDTO credentials);

  /**
   * Change password of Person.
   *
   * @param credentials
   */
  void changePassword(PersonLoginDTO credentials);

  /**
   * Change rights of Person.
   *
   * @param person
   * @param isAdmin
   */
  void changeRights(PersonDTO person, Boolean isAdmin);

  /**
   * Find Person by given id.
   *
   * @param id
   * @return {@link PersonDTO} if successfully found
   */
  PersonDTO findPersonById(Long id);

  /**
   * Find Person by given email.
   *
   * @param email
   * @return {@link PersonDTO} if successfully found
   */
  PersonDTO findPersonByEmail(String email);

  /**
   * Returns all existing Persons.
   *
   * @return list of existing Persons.
   */
  List<PersonDTO> findAllPersons();
}
