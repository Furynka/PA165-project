package com.blackteam.pipboy.service;
import com.blackteam.pipboy.persistence.entity.Person;
import com.blackteam.pipboy.persistence.dao.PersonDao;
import com.blackteam.pipboy.service.config.ServiceConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link PersonService} tests
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class PersonServiceTest {
    private Person person;
    private Person admin;

    @Mock
    private PersonDao personDao;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeTest() {
        person = new Person();
        person.setId(1L);
        person.setName("Prokop");
        person.setSurname("Buben");
        person.setEmail("prokop@buben.cz");
        person.setPassword("password");
        person.setAdministrator(false);
        admin = new Person();
        admin.setId(1L);
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setEmail("admin@google.com");
        admin.setPassword("admin");
        admin.setAdministrator(true);
    }

    @AfterMethod
    public void afterTest() {
        Mockito.reset(personDao);
    }

    @Test
    public void testRegisterPerson() {
        personService.registerPerson(person, person.getPassword());
        Mockito.verify(personDao).create(person);
    }

    @Test
    public void testRegisterPersonNullArgument() {
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.registerPerson(null, person.getPassword()));
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.registerPerson(person, null));
    }

    @Test
    public void testDeletePerson() {
        personService.deletePerson(person);
        Mockito.verify(personDao).delete(person);
    }

    @Test
    public void testDeletePersonNullArgument() {
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.deletePerson(null));
    }

    @Test
    public void testAuthenticate() {
        Assert.assertEquals(personService.authenticate(person, person.getPassword()), true);
    }

    @Test
    public void testAuthenticateNullArgument() {
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.authenticate(null, person.getPassword()));
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.authenticate(person, null));
    }

    @Test
    public void testIsAdmin() {
        Assert.assertEquals(personService.isAdmin(person), false);
        Assert.assertEquals(personService.isAdmin(admin), true);
    }

    @Test
    public void testIsAdminNullArgument() {
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.isAdmin(null));
    }

    @Test
    public void testChangePassword() {
        personService.changePassword(person, admin.getPassword());
        person.setPassword(admin.getPassword());
        Mockito.when(personDao.findById(person.getId())).thenReturn(person);
        Assert.assertEquals(personDao.findById(person.getId()).getPassword(), admin.getPassword());
    }

    @Test
    public void testChangePasswordNullArgument() {
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.changePassword(null, person.getPassword()));
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.changePassword(person, null));
    }

    @Test
    public void testChangeRights() {
        personService.changeRights(person, admin.getAdministrator());
        person.setAdministrator(admin.getAdministrator());
        Mockito.when(personDao.findById(person.getId())).thenReturn(person);
        Assert.assertEquals(personDao.findById(person.getId()).getAdministrator(), admin.getAdministrator());
    }

    @Test
    public void testChangeRightsNullArgument() {
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.changeRights(null, person.getAdministrator()));
        Assert.expectThrows(IllegalArgumentException.class, () -> personService.changeRights(person, null));
    }

    @Test
    public void testFindPersonById() {
        Mockito.when(personDao.findById(person.getId())).thenReturn(person);
        Assert.assertEquals(personService.findPersonById(person.getId()), person);
    }

    @Test
    public void testFindPersonByEmail() {
        Mockito.when(personDao.findByEmail(person.getEmail())).thenReturn(person);
        Assert.assertEquals(personService.findPersonByEmail(person.getEmail()), person);
    }

    @Test
    public void testFindAll() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        Mockito.when(personDao.findAll()).thenReturn(persons);
        List<Person> found = personService.findAll();
        Assert.assertEquals(found, persons);
    }
}