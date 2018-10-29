package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.AppContext;
import com.blackteam.pipboy.persistence.entity.Area;
import com.blackteam.pipboy.persistence.entity.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import javax.validation.ValidationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link AreaDaoImpl}
 *
 * @author janmichalov
 */
@ContextConfiguration(classes = AppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AreaDaoTest extends AbstractTestNGSpringContextTests {
    private Area areaOne;
    private Area areaTwo;
    private Monster monsterOne;


    @Autowired
    private AreaDao areaDao;

    @Autowired
    private MonsterDao monsterDao;

    @BeforeClass
    public void before() {
        areaOne = new Area();
        areaOne.setName("Brooklyn");
        areaOne.setDescription("This is totally not a test description, why should it be?!");

        areaTwo = new Area();
        areaTwo.setName("Minnesota");
        areaTwo.setDescription("I've not thought of anything close to being meaningful description.");

        monsterOne = new Monster();
        monsterOne.setAgility(25);
        monsterOne.setArea(areaOne);
        monsterOne.setHeight(9);
        monsterOne.setPower(13);
        monsterOne.setWeight(8);
        monsterOne.setName("Yaz");
        monsterOne.setSpeed(27);

        areaOne.addMonster(monsterOne);

        monsterDao.create(monsterOne);
        areaDao.create(areaOne);
        areaDao.create(areaTwo);
    }

    @AfterClass
    public void after() {
        areaDao.delete(areaOne);
        areaDao.delete(areaTwo);
        monsterDao.delete(monsterOne);
    }

    @Test
    public void testCreateArea() {
        Area area = new Area();
        area.setName("Wakanda");
        area.setDescription("I am gonna be created!!");

        Monster monster = new Monster();
        monster.setAgility(25);
        monster.setArea(areaOne);
        monster.setHeight(9);
        monster.setPower(13);
        monster.setWeight(8);
        monster.setName("Yaz");
        monster.setSpeed(27);

        area.addMonster(monster);
        areaDao.create(area);
        monsterDao.create(monster);

        Area foundArea = areaDao.findById(area.getId());

        assertThat(foundArea.getDescription())
                .isNotNull();
        assertThat(foundArea.getName())
                .isNotNull();

        foundArea.getMonsters();
        assertThat(foundArea.getMonsters())
                .contains(monster);

        monsterDao.delete(monster);
        areaDao.delete(area);
    }

    @Test
    public void testFindAreaById() {
        assertThat(areaDao.findById(areaOne.getId()))
                .isNotNull();
    }

    @Test
    public void testFindAreaByName() {
        assertThat(areaDao.findByName(areaOne.getName()))
                .isNotNull();
    }

    @Test
    public void testFindAllAreas() {
        List<Area> areas = areaDao.findAll();
        assertThat(areas).containsExactlyInAnyOrder(areaOne,areaTwo);
     }

    @Test
    public void testNoNameThrowsValidationException() {
        Area area = new Area();
        area.setDescription("Oh no, i am no name...");
        assertThatThrownBy(() -> areaDao.create(area)).isInstanceOf(ValidationException.class);
    }

    @Test
    public void testDeleteArea() {
        Area area = new Area();
        area.setName("Lunik IX.");
        area.setDescription("Where not even Chuck Norris has confidence to go to.");

        Long id = area.getId();
        areaDao.create(area);
        assertThat(areaDao.findById(id))
                .isNotNull();

        areaDao.delete(area);
        assertThat(areaDao.findById(id))
                .isNull();
    }

    @Test
    public void testUpdateArea() {
        Area area = areaDao.findById(areaOne.getId());
        String oldDescription = areaOne.getDescription();
        String oldName = areaOne.getName();
        area.setDescription("You'll wish it would kill you.");
        area.setName("Death Valley");

        areaDao.update(area);
        area = areaDao.findById(areaOne.getId());
        assertThat(area.getDescription()).isNotEqualTo(oldDescription);
        assertThat(area.getName()).isNotEqualTo(oldName);
    }

}
