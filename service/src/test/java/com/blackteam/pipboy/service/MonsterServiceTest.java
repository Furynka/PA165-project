package com.blackteam.pipboy.service;


import com.blackteam.pipboy.persistence.dao.MonsterDao;
import com.blackteam.pipboy.persistence.entity.Area;
import com.blackteam.pipboy.persistence.entity.Monster;
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
 * Tests for {@link MonsterServiceImpl}.
 *
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class MonsterServiceTest {

  private Monster monsterZombie;
  private Monster monsterBat;
  private Area area51;

  @Mock
  private MonsterDao monsterDao;

  @InjectMocks
  private MonsterServiceImpl monsterService;

  @BeforeClass
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @BeforeMethod
  public void beforeTest() {
    monsterZombie = new Monster();
    monsterZombie.setName("Zombie");
    monsterZombie.setId(1L);
    monsterZombie.setPower(5);
    monsterZombie.setAgility(3);
    monsterZombie.setSpeed(2);
    monsterZombie.setHeight(190);
    monsterZombie.setWeight(75);

    monsterBat = new Monster();
    monsterBat.setName("Bat");
    monsterBat.setId(2L);
    monsterBat.setPower(2);
    monsterBat.setAgility(8);
    monsterBat.setSpeed(8);
    monsterBat.setHeight(60);
    monsterBat.setWeight(25);

    area51 = new Area();
    area51.setName("Area 51");
    area51.setDescription("American base full with aliens");
    area51.setId(1L);
  }

  @AfterMethod
  public void afterTest() {
    Mockito.reset(monsterDao);
  }

  @Test
  public void createMonsterTest() {
    monsterService.create(monsterZombie);
    Mockito.verify(monsterDao).create(monsterZombie);
  }

  @Test
  public void createNotValidMonsterTest() {
    //Assert.assertThrows(NullPointerException.class, () -> monsterService.create(null));
  }

  @Test
  public void updateMonsterTest() {
    monsterService.update(monsterZombie);
    Mockito.verify(monsterDao).update(monsterZombie);
  }

  @Test
  public void deleteMonsterTest() {
    monsterService.delete(monsterZombie);
    Mockito.verify(monsterDao).delete(monsterZombie);
  }

  @Test
  public void findByIdTest() {
    Mockito.when(monsterDao.findById(monsterZombie.getId())).thenReturn(monsterZombie);
    Monster found = monsterService.findById(monsterZombie.getId());
    Assert.assertEquals(found, monsterZombie);
  }

  @Test
  public void findByName() {
    Mockito.when(monsterDao.findByName(monsterZombie.getName())).thenReturn(monsterZombie);
    Monster found = monsterService.findByName(monsterZombie.getName());
    Assert.assertEquals(found, monsterZombie);
  }

  @Test
  public void findAll() {
    List<Monster> allMonsters = new ArrayList<>();
    allMonsters.add(monsterZombie);
    allMonsters.add(monsterBat);

    Mockito.when(monsterDao.findAll()).thenReturn(allMonsters);
    List<Monster> found = monsterService.findAll();
    Assert.assertEquals(found, allMonsters);
  }

  @Test
  public void findTheStrongestMonsterTest() {
    List<Monster> allMonsters = new ArrayList<>();

    Mockito.when(monsterDao.findAll()).thenReturn(allMonsters);
    Monster found = monsterService.findTheStrongestMonster();
    Assert.assertNull(found);

    allMonsters.add(monsterBat);
    found = monsterService.findTheStrongestMonster();
    Assert.assertEquals(found, monsterBat);

    allMonsters.add(monsterZombie);
    found = monsterService.findTheStrongestMonster();
    Assert.assertEquals(found, monsterZombie);
  }

  @Test
  public void findAllMonstersFromSameArea() {
    monsterZombie.setArea(area51);
    List<Monster> monstersInArea = new ArrayList<>();
    monstersInArea.add(monsterZombie);
    List<Monster> allExistingMonsters = new ArrayList<>();
    allExistingMonsters.add(monsterZombie);
    allExistingMonsters.add(monsterBat);

    Mockito.when(monsterDao.findAll()).thenReturn(allExistingMonsters);
    List<Monster> found = monsterService.findAllMonstersFromSameArea(monsterZombie);
    Assert.assertEquals(found, monstersInArea);

    monsterBat.setArea(area51);
    found = monsterService.findAllMonstersFromSameArea(monsterZombie);
    Assert.assertEquals(found, allExistingMonsters);

    found = monsterService.findAllMonstersFromSameArea(monsterBat);
    Assert.assertEquals(found, allExistingMonsters);
  }


}
