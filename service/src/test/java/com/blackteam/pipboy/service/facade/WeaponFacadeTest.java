package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.WeaponCreateDTO;
import com.blackteam.pipboy.api.dto.WeaponDTO;
import com.blackteam.pipboy.persistence.entity.Weapon;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.WeaponService;
import com.blackteam.pipboy.service.config.ServiceConfig;
import com.blackteam.pipboy.persistence.enums.StatType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Weapon facade tests
 *
 * @author Jakub Havrila
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class WeaponFacadeTest {

    @Mock
    BeanMappingService beanMappingService;

    @Mock
    WeaponService weaponService;

    @InjectMocks
    WeaponFacadeImpl weaponFacade;

    private Weapon weapon1;
    private WeaponDTO weaponDTO1;
    private WeaponCreateDTO weaponCreateDTO1;

    @BeforeClass
    public void mocks() {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void init() {
        weaponCreateDTO1 = new WeaponCreateDTO();
        weaponCreateDTO1.setName("Ax");
        weaponCreateDTO1.setDescription("Stormbreaker");
        weaponCreateDTO1.setStatus(new HashSet<>(Arrays.asList(StatType.DARKNESS)));

        weapon1 = new Weapon();
        weapon1.setId(1L);
        weapon1.setName("Ax");

        weaponDTO1 = new WeaponDTO();
        weaponDTO1.setName("Ax");
    }

    @AfterMethod
    public void reset() {
        Mockito.reset(weaponService);
    }

    @Test
    public void weaponCreateTest() {
        Mockito.when(beanMappingService.mapTo(weaponCreateDTO1, Weapon.class)).thenReturn(weapon1);
        Mockito.when(weaponService.create(weapon1)).thenReturn(weapon1);
        Long id = weaponFacade.create(weaponCreateDTO1);
        Mockito.verify(weaponService).create(weapon1);
        assertThat(id).isEqualTo(weapon1.getId());
    }

    @Test
    public void weaponUpdateTest() {
        Mockito.when(beanMappingService.mapTo(weaponDTO1, Weapon.class)).thenReturn(weapon1);
        weaponFacade.update(weaponDTO1);
        Mockito.verify(weaponService).update(weapon1);
    }

    @Test
    public void findById() {
        Long id = weaponDTO1.getId();
        Mockito.when(weaponService.findById(id)).thenReturn(weapon1);
        Mockito.when(beanMappingService.mapTo(weapon1, WeaponDTO.class)).thenReturn(weaponDTO1);

        WeaponDTO weapon = weaponFacade.findById(id);
        Mockito.verify(weaponService).findById(id);
        assertThat(weapon).isEqualToComparingFieldByField(weaponDTO1);
    }

    @Test
    public void findByName() {
        String name = weaponDTO1.getName();
        Mockito.when(weaponService.findByName(name)).thenReturn(weapon1);
        Mockito.when(beanMappingService.mapTo(weapon1, WeaponDTO.class)).thenReturn(weaponDTO1);

        WeaponDTO weapon = weaponFacade.findByName(name);
        Mockito.verify(weaponService).findByName(name);
        assertThat(weapon).isEqualToComparingFieldByField(weaponDTO1);
    }

    @Test
    public void findAll() {
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(weapon1);
        List<WeaponDTO> weaponDTOS = new ArrayList<>();
        weaponDTOS.add(weaponDTO1);
        Mockito.when(weaponService.findAll()).thenReturn(weapons);
        Mockito.when(beanMappingService.mapTo(weapons, WeaponDTO.class)).thenReturn(weaponDTOS);
        List<WeaponDTO> weaponsList = weaponFacade.findAll();
        Mockito.verify(weaponService).findAll();
        assertThat(weaponsList.size()).isEqualTo(weapons.size());
    }

    @Test
    public void delete() {
        weaponDTO1.setId(weapon1.getId());
        Mockito.when(weaponService.findById(weaponDTO1.getId())).thenReturn(weapon1);
        weaponFacade.delete(weaponDTO1.getId());
        Mockito.verify(weaponService).delete(weaponDTO1.getId());
    }
}
