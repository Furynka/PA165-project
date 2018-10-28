package com.blackteam.pipboy.persistence.dao;


import com.blackteam.pipboy.persistence.AppContext;
import com.blackteam.pipboy.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Unit tests for {@link PersonDao}.
 *
 * @author Jiří Čechák
 * @since 2018-10-28
 */
@ContextConfiguration(classes = AppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends AbstractTestNGSpringContextTests {
  private Person user;
  private Person admin;
  private Person notSavedPerson;

  @Autowired
  private PersonDao personDao;

  @BeforeMethod
  public void beforeTest() {
    user = new Person();
    user.setName("Jan");
    user.setSurname("Novák");
    user.setEmail("jan.novak@gmail.com");
    user.setPassword("123456");
    user.setAdministrator(false);

    admin = new Person();
    admin.setName("Prokop");
    admin.setSurname("Buben");
    admin.setEmail("buben@seznam.cz");
    admin.setPassword("qwertasdf");
    admin.setAdministrator(true);

    notSavedPerson = new Person();
    notSavedPerson.setId(3L);
    notSavedPerson.setName("No");
    notSavedPerson.setSurname("One");
    notSavedPerson.setEmail("no.one@got.com");
    notSavedPerson.setPassword("0000000000");
    notSavedPerson.setAdministrator(false);

    personDao.create(user);
    personDao.create(admin);
  }

  @AfterMethod
  public void afterTest() {
    if (user != null) {
      Person person = personDao.findById(user.getId());
      if (person != null) {
        personDao.delete(person);
      }
    }

    if (admin != null) {
      Person person = personDao.findById(admin.getId());
      if (person != null) {
        personDao.delete(person);
      }
    }
  }

  @Test
  public void testFindByIdNotNull() {
    Person person = personDao.findById(user.getId());

    Assert.assertNotNull(person);
    Assert.assertNotEquals(person, admin);
    Assert.assertEquals(person, user);
  }

  @Test
  public void testFindByIdNull() {
    Person person = personDao.findById(notSavedPerson.getId());

    Assert.assertNull(person);
  }

  @Test
  public void testFindByIdIllegalArgumentException() {
    Assert.expectThrows(IllegalArgumentException.class, () -> personDao.findById(null));
  }

  @Test
  public void testFindByNameNotEmpty() {
    List<Person> persons = personDao.findByName(user.getName());

    Assert.assertEquals(persons.size(), 1);
    Assert.assertTrue(persons.contains(user));
    Assert.assertFalse(persons.contains(admin));
  }

  @Test
  public void testFindByNameEmpty() {
    List<Person> persons = personDao.findByName(notSavedPerson.getName());

    Assert.assertEquals(persons.size(), 0);
  }

  @Test
  public void testFindByNameIllegalArgumentException() {
    Assert.expectThrows(IllegalArgumentException.class, () -> personDao.findByName(null));
  }

  @Test
  public void testFindAll() {
    List<Person> persons = personDao.findAll();

    Assert.assertEquals(persons.size(), 2);
    Assert.assertTrue(persons.contains(user));
    Assert.assertTrue(persons.contains(admin));
    Assert.assertFalse(persons.contains(notSavedPerson));
  }

  @Test
  public void testFindAllEmpty() {
    personDao.delete(user);
    personDao.delete(admin);

    List<Person> persons = personDao.findAll();

    Assert.assertEquals(persons.size(), 0);
    Assert.assertFalse(persons.contains(user));
    Assert.assertFalse(persons.contains(admin));
    Assert.assertFalse(persons.contains(notSavedPerson));
  }

  @Test
  public void testFindBySurnameNotEmpty() {
    List<Person> persons = personDao.findBySurname(user.getSurname());

    Assert.assertEquals(persons.size(), 1);
    Assert.assertTrue(persons.contains(user));
    Assert.assertFalse(persons.contains(admin));
  }

  @Test
  public void testFindBySurnameEmpty() {
    List<Person> persons = personDao.findBySurname(notSavedPerson.getSurname());

    Assert.assertEquals(persons.size(), 0);
  }

  @Test
  public void testFindBySurnameIllegalArgumentException() {
    Assert.expectThrows(IllegalArgumentException.class, () -> personDao.findBySurname(null));
  }

  @Test
  public void testFindByEmailNotNull() {
    Person person = personDao.findByEmail(user.getEmail());

    Assert.assertNotNull(person);
    Assert.assertNotEquals(person, admin);
    Assert.assertEquals(person, user);
  }

  @Test
  public void testFindByEmailNull() {
    Person person = personDao.findByEmail(notSavedPerson.getEmail());

    Assert.assertNull(person);
  }

  @Test
  public void testFindByEmailIllegalArgumentException() {
    Assert.expectThrows(IllegalArgumentException.class, () -> personDao.findByEmail(null));
  }

  @Test
  public void testCreate() {
    Person newPerson = new Person();
    newPerson.setName("New");
    newPerson.setSurname("Person");
    newPerson.setPassword("123");
    newPerson.setAdministrator(false);
    newPerson.setEmail("mail@mail.com");

    personDao.create(newPerson);

    Person person = personDao.findById(newPerson.getId());

    Assert.assertNotNull(person);
    Assert.assertEquals(person, newPerson);

    personDao.delete(newPerson);
  }

  @Test
  public void testUpdate() {
    Person person = personDao.findById(user.getId());

    Assert.assertNotNull(person);
    Assert.assertEquals(person.getName(), user.getName());
    Assert.assertEquals(person.getSurname(), user.getSurname());
    Assert.assertEquals(person.getEmail(), user.getEmail());
    Assert.assertEquals(person.getPassword(), user.getPassword());
    Assert.assertEquals(person.getAdministrator(), user.getAdministrator());

    user.setName("New Name");
    user.setSurname("New Surname");
    user.setEmail("New Email");
    user.setPassword("New Password");
    user.setAdministrator(true);

    Person modifiedPerson = personDao.findById(user.getId());

    Assert.assertNotNull(modifiedPerson);
    Assert.assertEquals(modifiedPerson.getName(), "New Name");
    Assert.assertEquals(modifiedPerson.getSurname(), "New Surname");
    Assert.assertEquals(modifiedPerson.getEmail(), "New Email");
    Assert.assertEquals(modifiedPerson.getPassword(), "New Password");
    Assert.assertTrue(modifiedPerson.getAdministrator());
  }

  @Test
  public void testDelete() {
    Person person = personDao.findById(user.getId());

    Assert.assertNotNull(person);
    Assert.assertEquals(person, user);

    personDao.delete(user);

    Person deletedPerson = personDao.findById(user.getId());

    Assert.assertNull(deletedPerson);

    Person existingPerson = personDao.findById(admin.getId());

    Assert.assertNotNull(existingPerson);
  }
}
