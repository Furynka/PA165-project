package com.blackteam.pipboy.service.facade;

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
 * Tests for {@link com.blackteam.pipboy.api.facade.MonsterFacade}
 *
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class MonsterFacadeTest {

  private MonsterDTO monsterZombieDTO;
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
    Mockito.when(beanMapping.mapTo(monsterZombieDTO, Monster.class)).thenReturn(monsterZombieEntity);

    monsterFacade.create(monsterZombieDTO);
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

}