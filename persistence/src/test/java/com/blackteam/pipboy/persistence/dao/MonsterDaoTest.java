package com.blackteam.pipboy.persistence.dao;


import com.blackteam.pipboy.persistence.AppContext;
import com.blackteam.pipboy.persistence.entity.Monster;
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
 * Unit tests for {@link MonsterDao}.
 *
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = AppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MonsterDaoTest extends AbstractTestNGSpringContextTests {
  private Monster monsterZombie;
  private Monster monsterBat;

  @Autowired
  private MonsterDao monsterDao;
  @Autowired
  private AreaDao areaDao;

  @BeforeMethod
  public void createObjects() {
    monsterZombie = new Monster();
    monsterZombie.setName("Zombie");
    monsterZombie.setPower(5);
    monsterZombie.setAgility(3);
    monsterZombie.setHeight(190);
    monsterZombie.setWeight(75);
    monsterZombie.setSpeed(2);

    monsterDao.create(monsterZombie);

    monsterBat = new Monster();
    monsterBat.setName("Bat");
    monsterBat.setPower(2);
    monsterBat.setAgility(8);
    monsterBat.setHeight(50);
    monsterBat.setWeight(5);
    monsterBat.setSpeed(8);

    monsterDao.create(monsterBat);
  }

  @AfterMethod
  public void clear() {
    if (monsterDao.findById(monsterZombie.getId()) != null) {
      monsterDao.delete(monsterDao.findById(monsterZombie.getId()));
    }
    if (monsterDao.findById(monsterBat.getId()) != null) {
      monsterDao.delete(monsterDao.findById(monsterBat.getId()));
    }
  }

  @Test
  public void testCreateMonster() {
    List<Monster> foundMonsters = monsterDao.findAll();

    Assert.assertNotNull(foundMonsters);
    Assert.assertEquals(foundMonsters.size(), 2);
  }

  @Test
  public void testDeleteMonster() {
    Monster foundBat = monsterDao.findById(monsterBat.getId());
    Assert.assertNotNull(foundBat);

    monsterDao.delete(foundBat);
    foundBat = monsterDao.findById(monsterBat.getId());
    Assert.assertNull(foundBat);
    List<Monster> foundMonsters = monsterDao.findAll();
    Assert.assertEquals(foundMonsters.size(), 1);
  }

  @Test
  public void testUpdateMonster() {
    Monster foundBat = monsterDao.findById(monsterBat.getId());
    Assert.assertEquals(foundBat.getName(), monsterBat.getName());
    Assert.assertEquals(foundBat.getPower(), monsterBat.getPower());
    Assert.assertEquals(foundBat.getAgility(), monsterBat.getAgility());

    monsterBat.setName("Batman");
    monsterBat.setPower(monsterBat.getPower() + 3);
    monsterBat.setPower(monsterBat.getAgility() + 3);

    monsterDao.update(monsterBat);

    foundBat = monsterDao.findById(monsterBat.getId());
    Assert.assertEquals(foundBat.getName(), monsterBat.getName());
    Assert.assertEquals(foundBat.getPower(), monsterBat.getPower());
    Assert.assertEquals(foundBat.getAgility(), monsterBat.getAgility());
  }

  @Test
  public void testFindAll() {
    List<Monster> foundMonsters = monsterDao.findAll();
    Assert.assertEquals(foundMonsters.size(), 2);
    Assert.assertTrue(foundMonsters.contains(monsterZombie));
    Assert.assertTrue(foundMonsters.contains(monsterBat));


    monsterDao.delete(monsterDao.findById(monsterZombie.getId()));
    foundMonsters = monsterDao.findAll();
    Assert.assertEquals(foundMonsters.size(), 1);
    Assert.assertTrue(foundMonsters.contains(monsterBat));
  }

  @Test
  public void testFindById() {
    Monster foundMonster = monsterDao.findById(monsterZombie.getId());
    Assert.assertEquals(foundMonster, monsterZombie);
    Assert.assertNotEquals(foundMonster, monsterBat);

    foundMonster = monsterDao.findById(monsterBat.getId());
    Assert.assertEquals(foundMonster, monsterBat);
  }

  @Test
  public void testFindByName() {
    Monster foundMonster = monsterDao.findByName(monsterZombie.getName());
    Assert.assertEquals(foundMonster, monsterZombie);
    Assert.assertNotEquals(foundMonster, monsterBat);
  }

}
