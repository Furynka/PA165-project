package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.PersonDTO;
import com.blackteam.pipboy.api.dto.PersonLoginDTO;
import com.blackteam.pipboy.api.facade.PersonFacade;

import java.util.List;

/**
 * Implementation class for {@link PersonFacade}.
 *
 * @author Jiri Oliva
 */
public class PersonFacadeImpl implements PersonFacade {
  @Override
  public PersonDTO registerPerson(PersonDTO person, String password) {
    return null;
  }

  @Override
  public boolean authenticate(PersonLoginDTO credentials) {
    return false;
  }

  @Override
  public void changePassword(PersonLoginDTO credentials) {

  }

  @Override
  public void changeRights(PersonDTO person, Boolean isAdmin) {

  }

  @Override
  public PersonDTO findPersonById(Long id) {
    return null;
  }

  @Override
  public PersonDTO findPersonByEmail(String email) {
    return null;
  }

  @Override
  public List<PersonDTO> findAllPersons() {
    return null;
  }
}
