package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.AppContext;
import com.blackteam.pipboy.persistence.entity.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
/**
 * Unit tests for Weapon DAO
 *
 * @author Jakub Havrila
 */

@ContextConfiguration(classes = AppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class WeaponDaoTest extends AbstractTestNGSpringContextTests {
    private Weapon weapon1;
    private Weapon weapon2;

    @Autowired
    private WeaponDao weaponDao;

    @BeforeMethod
    public void init() {
        weapon1 = new Weapon();
        weapon1.setName("Ax");
        weaponDao.create(weapon1);

        weapon2 = new Weapon();
        weapon2.setName("Gun");
        weaponDao.create(weapon2);
    }

    @Test
    public void createNullWeaponTest() {
        assertThatThrownBy(() -> weaponDao.create(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createNullNameTest() {
        Weapon weapon = new Weapon();
        assertThatThrownBy(() -> weaponDao.create(weapon)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void createWeaponTest() {
        Weapon weapon = new Weapon();
        weapon.setName("New weapon");
        weaponDao.create(weapon);
        List<Weapon> weapons = weaponDao.findAll();
        assertThat(weapons).contains(weapon);
    }

    @Test
    public void updateWeaponTest() {
        weapon1.setName("Updated name");
        weaponDao.update(weapon1);
        Assert.assertEquals(weaponDao.findById(weapon1.getId()).getName(), "Updated name");
    }

    @Test
    public void deleteNullWeaponTest() {
        assertThatThrownBy(() -> weaponDao.delete(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void deleteWeaponTest() {
        weaponDao.delete(weapon1);
        List<Weapon> weapons = weaponDao.findAll();
        Assert.assertEquals(weapons.size(), 1);
    }

    @Test
    public void findAllTest() {
        Assert.assertEquals(weaponDao.findAll().size(), 2);
    }

    @Test
    public void findByNullIdTest() {
        assertThatThrownBy(() -> weaponDao.findById(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void findByBadIdTest() {
        Weapon weapon = new Weapon();
        assertThat(weaponDao.findById(weapon.getId())).isNull();
    }

    @Test
    public void findByIdTest() {
        Weapon weapon = weaponDao.findById(weapon1.getId());
        Assert.assertEquals(weapon, weapon1);
    }

    @Test
    public void findByNullNameTest() {
        assertThatThrownBy(() -> weaponDao.findByName(null)).isInstanceOf(NoResultException.class);
    }

    @Test
    public void findByBadNameTest() {
        assertThatThrownBy(() -> weaponDao.findByName("Bad name")).isInstanceOf(NoResultException.class);
    }

    @Test
    public void findByName() {
        Weapon weapon = weaponDao.findByName(weapon1.getName());
        Assert.assertEquals(weapon, weapon1);
    }
}
