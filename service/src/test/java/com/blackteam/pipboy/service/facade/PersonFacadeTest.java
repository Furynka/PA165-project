package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.PersonDTO;
import com.blackteam.pipboy.api.dto.PersonLoginDTO;
import com.blackteam.pipboy.api.facade.PersonFacade;
import com.blackteam.pipboy.persistence.entity.Person;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.PersonService;
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
 * {@link PersonFacade} tests
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class PersonFacadeTest {

    private PersonDTO personDTO;
    private PersonLoginDTO personLoginDTO;
    private Person person;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonFacadeImpl personFacade;

    @Mock
    private BeanMappingService beanMapping;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeTest() {
        personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setName("Prokop");
        personDTO.setSurname("Buben");
        personDTO.setEmail("prokop@buben.cz");
        personDTO.setPassword("password");
        personDTO.setAdministrator(false);

        personLoginDTO = new PersonLoginDTO();
        personLoginDTO.setEmail(personDTO.getEmail());
        personLoginDTO.setPassword(personDTO.getPassword());
        personLoginDTO.setNewPassword(personDTO.getPassword());

        person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setEmail(personDTO.getEmail());
        person.setPassword(personDTO.getPassword());
        person.setAdministrator(false);

        Mockito.when(beanMapping.mapTo(person, PersonDTO.class)).thenReturn(personDTO);
        Mockito.when(beanMapping.mapTo(personDTO, Person.class)).thenReturn(person);
    }

    @AfterMethod
    public void afterTest() {
        Mockito.reset(personService);
    }

    @Test
    public void testRegisterPerson() {
        personFacade.registerPerson(personDTO, personDTO.getPassword());
        Mockito.verify(personService).registerPerson(person, person.getPassword());
    }

    @Test
    public void testDeletePerson() {
        long id = personDTO.getId();
        Mockito.when(personService.findPersonById(id)).thenReturn(person);
        personFacade.deletePerson(personDTO.getId());
        Mockito.verify(personService).deletePerson(person);
    }

    @Test
    public void testAuthenticate() {
        Mockito.when(personService.findPersonByEmail(person.getEmail())).thenReturn(person);
        Mockito.when(personService.authenticate(person, personDTO.getPassword())).thenReturn(false);

        Assert.assertEquals(personFacade.authenticate(personLoginDTO), false);

        personFacade.registerPerson(personDTO, personDTO.getPassword());
        Mockito.verify(personService).registerPerson(person, person.getPassword());
        Mockito.when(personService.authenticate(person, personDTO.getPassword())).thenReturn(true);

        Assert.assertEquals(personFacade.authenticate(personLoginDTO), true);
    }

    @Test
    public void testIsAdmin() {
        Mockito.when(personService.isAdmin(person)).thenReturn(false);
        Assert.assertEquals(personFacade.isAdmin(personDTO), false);
    }

    @Test
    public void testChangePassword() {
        personLoginDTO.setNewPassword("new");
        Mockito.when(personService.findPersonByEmail(person.getEmail())).thenReturn(person);
        personFacade.changePassword(personLoginDTO);
        person.setPassword(personLoginDTO.getNewPassword());
        Mockito.when(personService.findPersonByEmail(person.getEmail())).thenReturn(person);
        Mockito.verify(personService).changePassword(person, personLoginDTO.getNewPassword());
    }

    @Test
    public void testChangeRights() {
        personFacade.changeRights(personDTO, false);
        Mockito.verify(personService).changeRights(person, false);
    }

    @Test
    public void testFindPersonById() {
        Mockito.when(personService.findPersonById(personDTO.getId())).thenReturn(person);
        PersonDTO foundPersonDTO = personFacade.findPersonById(personDTO.getId());
        Mockito.verify(personService).findPersonById(personDTO.getId());
        Assert.assertEquals(foundPersonDTO, personDTO);
    }

    @Test
    public void testFindPersonByEmail() {
        Mockito.when(personService.findPersonByEmail(personDTO.getEmail())).thenReturn(person);
        PersonDTO foundPersonDTO = personFacade.findPersonByEmail(personDTO.getEmail());
        Mockito.verify(personService).findPersonByEmail(personDTO.getEmail());
        Assert.assertEquals(foundPersonDTO, personDTO);
    }

    @Test
    public void testFindAll() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        List<PersonDTO> personsDTO = new ArrayList<>();
        personsDTO.add(personDTO);

        Mockito.when(beanMapping.mapTo(persons, PersonDTO.class)).thenReturn(personsDTO);
        Mockito.when(personService.findAll()).thenReturn(persons);
        List<PersonDTO> foundPersonsDTO = personFacade.findAllPersons();
        Mockito.verify(personService).findAll();
        Assert.assertEquals(foundPersonsDTO.size(), persons.size());
    }

}
