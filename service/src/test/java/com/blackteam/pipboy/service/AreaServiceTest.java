package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.AreaDao;
import com.blackteam.pipboy.persistence.entity.Area;
import com.blackteam.pipboy.persistence.entity.Monster;
import com.blackteam.pipboy.service.config.ServiceConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ServiceConfig.class)
public class AreaServiceTest {

    @Mock
    private AreaDao areaDao;

    @Autowired
    @InjectMocks
    private AreaServiceImpl areaService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Area areaOne;

    private Area areaTwo;

    private Monster adam;

    private Monster george;

    @BeforeMethod
    public void before() {
        areaOne = new Area();
        areaTwo = new Area();

        adam = new Monster();
        adam.setName("Adam");
        adam.setArea(areaOne);
        adam.setId(1L);
        adam.setPower(3);
        adam.setAgility(4);
        adam.setSpeed(10);
        adam.setHeight(160);
        adam.setWeight(70);

        george = new Monster();
        george.setName("Adam");
        george.setArea(areaTwo);
        george.setId(2L);
        george.setPower(10);
        george.setAgility(2);
        george.setSpeed(9000);
        george.setHeight(180);
        george.setWeight(100);


        areaOne.setName("Wisconsin");
        areaOne.setId(1L);
        areaOne.setDescription("This is areaOne");
        areaOne.addMonster(adam);

        areaTwo.setName("Tahiti");
        areaTwo.setId(2L);
        areaTwo.setDescription("This is areaTwo");
        areaTwo.addMonster(george);
    }

    @AfterMethod
    public void after() {
        Mockito.reset(areaDao);
    }

    @Test
    public void createNullAreaTest() {
        assertThatThrownBy(() -> areaService.create(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void updateNullAreaTest() {
        assertThatThrownBy(() -> areaService.update(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void removeNullAreaTest() {
        assertThatThrownBy(() -> areaService.remove(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void findByNullIdAreaTest() {
        assertThatThrownBy(() -> areaService.findById(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void findByNullNameAreaTest() {
        assertThatThrownBy(() -> areaService.create(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createAreaTest() {
        areaService.create(areaOne);
        Mockito.verify(areaDao).create(areaOne);
    }

    @Test
    public void updateAreaTest() {
        areaService.update(areaOne);
        Mockito.verify(areaDao).update(areaOne);
    }

    @Test
    public void removeAreaTest() {
        areaService.remove(areaOne);
        Mockito.verify(areaDao).delete(areaOne);
    }

    @Test
    public void findById() {
        Mockito.when(areaDao.findById(areaOne.getId())).thenReturn(areaOne);
        assertThat(areaService.findById(areaOne.getId())).isEqualTo(areaOne);
        Mockito.verify(areaDao).findById(areaOne.getId());
    }

    @Test
    public void findAreaByName() {
        Mockito.when(areaDao.findByName(areaOne.getName())).thenReturn(areaOne);
        assertThat(areaService.findByName(areaOne.getName())).isEqualTo(areaOne);
        Mockito.verify(areaDao).findByName(areaOne.getName());
    }

    @Test
    public void findAllAreas() {
        List<Area> areaList = new ArrayList<>();
        areaList.add(areaOne);
        areaList.add(areaTwo);
        Mockito.when(areaDao.findAll()).thenReturn(areaList);
        assertThat(areaService.findAll())
                .containsExactlyInAnyOrder(areaOne,areaTwo);
        Mockito.verify(areaDao).findAll();
    }
}
