package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.WeaponDao;
import com.blackteam.pipboy.persistence.entity.Weapon;
import com.blackteam.pipboy.service.config.ServiceConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Weapon service tests
 *
 * @author Jakub Havrila
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class WeaponServiceTest{

    @Mock
    private WeaponDao weaponDao;

    @InjectMocks
    private WeaponServiceImpl weaponService;

    private Weapon weapon1;
    private Weapon weapon2;

    @BeforeClass
    public void mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void init() {
        weapon1 = new Weapon();
        weapon1.setId(1L);
        weapon1.setName("Ax");

        weapon2 = new Weapon();
        weapon2.setId(2L);
        weapon2.setName("Hammer");
    }

    @Test
    public void createWeaponTest() {
        weaponService.create(weapon1);
        Mockito.verify(weaponDao).create(weapon1);
    }

    @Test
    public void createNullTest() {
        assertThatThrownBy(() -> weaponService.create(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void updateWeaponTest() {
        weaponService.update(weapon1);
        Mockito.verify(weaponDao).update(weapon1);
    }

    @Test
    public void updateNullTest() {
        assertThatThrownBy(() -> weaponService.update(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(weaponDao.findById(weapon1.getId())).thenReturn(weapon1);
        Weapon weapon = weaponService.findById(weapon1.getId());
        assertThat(weapon).isEqualToComparingFieldByField(weapon1);
    }

    @Test
    public void findByNullIdTest () {
        assertThatThrownBy(() -> weaponService.findById(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void findByName() {
        Mockito.when(weaponDao.findByName(weapon1.getName())).thenReturn(weapon1);
        Weapon weapon = weaponService.findByName(weapon1.getName());
        assertThat(weapon).isEqualToComparingFieldByField(weapon1);
    }

    @Test
    public void findByNullNameTest() {
        assertThatThrownBy(() -> weaponService.findByName(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void findAll() {
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(weapon1);
        weapons.add(weapon2);
        Mockito.when(weaponDao.findAll()).thenReturn(weapons);
        List<Weapon> weaponList = weaponService.findAll();
        assertThat(weapons).isEqualTo(weaponList);
    }
}
