package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.MonsterCreateDTO;
import com.blackteam.pipboy.api.dto.MonsterDTO;
import com.blackteam.pipboy.persistence.entity.Monster;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.MonsterService;
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
 * Tests for {@link MonsterFacadeImpl}
 *
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class MonsterFacadeTest {

  private MonsterDTO monsterZombieDTO;
  private MonsterCreateDTO monsterCreateDTO;
  private Monster monsterZombieEntity;

  @Mock
  private MonsterService monsterService;

  @InjectMocks
  private MonsterFacadeImpl monsterFacade;

  @Mock
  private BeanMappingService beanMapping;

  @BeforeClass
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @BeforeMethod
  public void beforeTest() {
    monsterZombieDTO = new MonsterDTO();
    monsterZombieDTO.setName("Zombie");
    monsterZombieDTO.setId(1L);
    monsterZombieDTO.setPower(5);
    monsterZombieDTO.setAgility(3);
    monsterZombieDTO.setSpeed(2);
    monsterZombieDTO.setHeight(190);
    monsterZombieDTO.setWeight(75);

    monsterCreateDTO = new MonsterCreateDTO();
    monsterCreateDTO.setName("Zombie");
    monsterCreateDTO.setPower(5);
    monsterCreateDTO.setAgility(3);
    monsterCreateDTO.setSpeed(2);
    monsterCreateDTO.setHeight(190);
    monsterCreateDTO.setWeight(75);

    monsterZombieEntity = new Monster();
    monsterZombieEntity.setName(monsterZombieDTO.getName());
    monsterZombieEntity.setId(monsterZombieDTO.getId());
    monsterZombieEntity.setPower(monsterZombieDTO.getPower());
    monsterZombieEntity.setAgility(monsterZombieDTO.getAgility());
    monsterZombieEntity.setSpeed(monsterZombieDTO.getSpeed());
    monsterZombieEntity.setHeight(monsterZombieDTO.getHeight());
    monsterZombieEntity.setWeight(monsterZombieDTO.getWeight());
  }

  @AfterMethod
  public void afterTest() {
    Mockito.reset(monsterService);
  }

  @Test
  public void createMonsterTest() {
    Mockito.when(beanMapping.mapTo(monsterCreateDTO, Monster.class)).thenReturn(monsterZombieEntity);
    Mockito.when(monsterService.create(monsterZombieEntity)).thenReturn(monsterZombieEntity);
    monsterFacade.create(monsterCreateDTO);
    Mockito.verify(monsterService).create(monsterZombieEntity);
  }

  @Test
  public void updateMonsterTest() {
    Mockito.when(beanMapping.mapTo(monsterZombieDTO, Monster.class)).thenReturn(monsterZombieEntity);

    monsterFacade.update(monsterZombieDTO);
    Mockito.verify(monsterService).update(monsterZombieEntity);
  }

  @Test
  public void deleteMonsterTest() {
    Mockito.when(beanMapping.mapTo(monsterZombieDTO, Monster.class)).thenReturn(monsterZombieEntity);

    monsterFacade.delete(monsterZombieDTO);
    Mockito.verify(monsterService).delete(monsterZombieEntity);
  }

  @Test
  public void findById() {
    Long id = monsterZombieDTO.getId();
    Mockito.when(monsterService.findById(id)).thenReturn(monsterZombieEntity);
    Mockito.when(beanMapping.mapTo(monsterZombieEntity, MonsterDTO.class)).thenReturn(monsterZombieDTO);

    MonsterDTO found = monsterFacade.findById(id);
    Mockito.verify(monsterService).findById(id);
    Assert.assertEquals(found, monsterZombieDTO);
  }

  @Test
  public void findByName() {
    String name = monsterZombieDTO.getName();
    Mockito.when(monsterService.findByName(name)).thenReturn(monsterZombieEntity);
    Mockito.when(beanMapping.mapTo(monsterZombieEntity, MonsterDTO.class)).thenReturn(monsterZombieDTO);

    MonsterDTO found = monsterFacade.findByName(name);
    Mockito.verify(monsterService).findByName(name);
    Assert.assertEquals(found, monsterZombieDTO);
  }

  @Test
  public void findAll() {
    List<Monster> monsters = new ArrayList<>();
    monsters.add(monsterZombieEntity);
    List<MonsterDTO> monsterDTOs = new ArrayList<>();
    monsterDTOs.add(monsterZombieDTO);

    Mockito.when(monsterService.findAll()).thenReturn(monsters);
    Mockito.when(beanMapping.mapTo(monsters, MonsterDTO.class)).thenReturn(monsterDTOs);
    List<MonsterDTO> found = monsterFacade.findAll();
    Mockito.verify(monsterService).findAll();
    Assert.assertEquals(found.size(), monsters.size());
  }

  @Test
  public void findAllMonstersFromSameAreaTest() {
    List<Monster> monsters = new ArrayList<>();
    monsters.add(monsterZombieEntity);
    List<MonsterDTO> monsterDTOs = new ArrayList<>();
    monsterDTOs.add(monsterZombieDTO);

    Mockito.when(beanMapping.mapTo(monsterZombieDTO, Monster.class)).thenReturn(monsterZombieEntity);
    Mockito.when(beanMapping.mapTo(monsters, MonsterDTO.class)).thenReturn(monsterDTOs);
    Mockito.when(monsterService.findAllMonstersFromSameArea(monsterZombieEntity)).thenReturn(monsters);

    List<MonsterDTO> found = monsterFacade.findAllMonstersFromSameArea(monsterZombieDTO);
    Mockito.verify(monsterService).findAllMonstersFromSameArea(monsterZombieEntity);
    Assert.assertEquals(found, monsterDTOs);
  }

  @Test
  public void findTheStrongestMonster() {
    Mockito.when(monsterService.findTheStrongestMonster()).thenReturn(monsterZombieEntity);
    Mockito.when(beanMapping.mapTo(monsterZombieEntity, MonsterDTO.class)).thenReturn(monsterZombieDTO);

    MonsterDTO found = monsterFacade.findTheStrongestMonster();
    Mockito.verify(monsterService).findTheStrongestMonster();
    Assert.assertEquals(found, monsterZombieDTO);
  }

}
