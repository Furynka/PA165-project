package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.MonsterDTO;
import com.blackteam.pipboy.api.facade.MonsterFacade;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.MonsterService;
import com.blackteam.pipboy.service.config.ServiceConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for {@link com.blackteam.pipboy.api.facade.MonsterFacade}
 *
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class MonsterFacadeTest {

  private MonsterDTO monsterZombieDTO;

  @Mock
  private MonsterService monsterService;

  @InjectMocks
  private MonsterFacade monsterFacade;

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
    monsterZombieDTO.setPower(5);
    monsterZombieDTO.setAgility(3);
    monsterZombieDTO.setHeight(190);
    monsterZombieDTO.setWeight(75);
    monsterZombieDTO.setSpeed(2);



  }

  @Test
  public void createMonsterTest() {
    //TODO
  }

  @Test
  public void updateMonsterTest() {
    //TODO
  }
}
