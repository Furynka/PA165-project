package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.AreaDTO;
import com.blackteam.pipboy.api.dto.MonsterDTO;
import com.blackteam.pipboy.persistence.entity.Area;
import com.blackteam.pipboy.persistence.entity.Monster;
import com.blackteam.pipboy.service.AreaService;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.config.ServiceConfig;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ServiceConfig.class)
public class AreaFacadeTest {
    private Area area;

    private AreaDTO areaDTO;

    private Monster monster;

    private MonsterDTO monsterDTO;

    @Mock
    private AreaService areaService;

    @InjectMocks
    private AreaFacadeImpl areaFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void before() {
        monster = new Monster();
        monster.setName("Johny");

        monsterDTO = new MonsterDTO();
        monsterDTO.setName("Johny");

        area = new Area();
        area.setId(1L);
        area.addMonster(monster);
        area.setName("Alcatraz");
        area.setDescription("not too long description");

        areaDTO = new AreaDTO();
        areaDTO.setId(1L);
        areaDTO.addMonster(monsterDTO);
        areaDTO.setName("Alcatraz");
        areaDTO.setDescription("not too long description");
    }

    @AfterMethod
    public void after() {
        Mockito.reset(areaService);
    }

    @Test
    public void createAreaTest() {
        Mockito.when(beanMappingService.mapTo(areaDTO,Area.class)).thenReturn(area);
        Mockito.when(areaService.create(area)).thenReturn(area);
        areaFacade.create(areaDTO);
        Mockito.verify(areaService).create(area);
    }

    @Test
    public void updateNameAreaTest() {
        Mockito.when(areaService.findById(areaDTO.getId())).thenReturn(area);
        Mockito.when(beanMappingService.mapTo(areaDTO,Area.class)).thenReturn(area);
        area.setDescription("some name");
        areaFacade.updateName(areaDTO.getId(),"some name");
        Mockito.verify(areaService).update(area);

    }

    @Test
    public void updateDescriptionAreaTest() {
        Mockito.when(areaService.findById(areaDTO.getId())).thenReturn(area);
        Mockito.when(beanMappingService.mapTo(areaDTO,Area.class)).thenReturn(area);
        area.setDescription("something");
        areaFacade.updateDescription(areaDTO.getId(),"something");
        Mockito.verify(areaService).update(area);
    }
    @Test
    public void deleteAreaTest() {
        Mockito.when(areaService.findById(areaDTO.getId())).thenReturn(area);
        areaFacade.delete(areaDTO.getId());
        Mockito.verify(areaService).remove(area);
    }

    @Test
    public void findByIdAreaTest() {
        Mockito.when(beanMappingService.mapTo(area,AreaDTO.class)).thenReturn(areaDTO);

        Mockito.when(areaService.findById(areaDTO.getId())).thenReturn(area);
        AreaDTO areaDTO1 = areaFacade.findById(areaDTO.getId());
        Mockito.verify(areaService).findById(areaDTO.getId());

        assertThat(areaDTO1).isEqualTo(areaDTO);
    }


    @Test
    public void findByNameAreaTest() {
        Mockito.when(beanMappingService.mapTo(area,AreaDTO.class)).thenReturn(areaDTO);
        Mockito.when(areaService.findByName(areaDTO.getName())).thenReturn(area);
        AreaDTO areaDTO1 = areaFacade.findByName(areaDTO.getName());
        Mockito.verify(areaService).findByName(areaDTO.getName());

        assertThat(areaDTO1).isEqualTo(areaDTO);
    }

    @Test
    public void findAllAreaTest() {
        List<Area> areaList = new ArrayList<>();
        areaList.add(area);
        List<AreaDTO> areaDTOList = new ArrayList<>();
        areaDTOList.add(areaDTO);

        Mockito.when(areaService.findAll()).thenReturn(areaList);
        Mockito.when(beanMappingService.mapTo(areaList,AreaDTO.class)).thenReturn(areaDTOList);

        Assert.assertEquals(areaFacade.findAll(),areaDTOList);
    }


}
