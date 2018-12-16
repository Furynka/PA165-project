package com.blackteam.pipboy.data.facade;

import com.blackteam.pipboy.persistence.entity.Person;
import com.blackteam.pipboy.persistence.entity.Weapon;
import com.blackteam.pipboy.persistence.enums.StatType;
import com.blackteam.pipboy.service.PersonService;
import com.blackteam.pipboy.service.WeaponService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

  @Inject
  private WeaponService weaponService;

  @Override
  public void loadData() {
      loadPersons();
      loadWeapons();
  }

  private void loadPersons() {
      createPerson("George", "Woodland", "george20@gmail.com", "password", true);
      createPerson("Lucas", "Oakland", "lucaluca@gmail.com", "password", false);
      createPerson("Anastasia", "Smithy", "anastasia@gmail.com", "password", false);
      createPerson("admin","admin","admin@admin.com","admin", true);
  }

  private void createPerson(String name, String surname, String email, String password, boolean isAdmin) {
    Person person = new Person();
    person.setName(name);
    person.setSurname(surname);
    person.setEmail(email);
    person.setPassword(password);
    person.setAdministrator(isAdmin);

    personService.registerPerson(person);
  }

  private void loadWeapons() {
      createWeapon("MP4", "Very soundy type of pistol",
              new HashSet<>(Arrays.asList(StatType.DARKNESS, StatType.EARTH)));
      createWeapon("Mjolnir", "Can you even lift?",
              new HashSet<>(Arrays.asList(StatType.THUNDER,StatType.WIND)));
      createWeapon("Axiom Perpetuum", "The worst of Axiom were imprisoned by more than iron.",
              new HashSet<>(Arrays.asList(StatType.FIRE,StatType.WATER,StatType.THUNDER)));

  }
  private void createWeapon(String name, String description, Set<StatType> status) {
      Weapon weapon = new Weapon();
      weapon.setName(name);
      weapon.setDescription(description);
      weapon.setStatus(status);

      weaponService.create(weapon);
  }
}
