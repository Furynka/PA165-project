package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.MonsterDTO;
import com.blackteam.pipboy.api.facade.MonsterFacade;
import com.blackteam.pipboy.persistence.entity.Monster;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Monster facade implementation
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
@Service
@Transactional
public class MonsterFacadeImpl implements MonsterFacade {

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(MonsterDTO monster) {
        Monster monsterEntity = beanMappingService.mapTo(monster, Monster.class);
        monsterEntity = monsterService.create(monsterEntity);
        return monsterEntity.getId();
    }

    @Override
    public void update(MonsterDTO monster) {
        Monster monsterEntity = beanMappingService.mapTo(monster, Monster.class);
        monsterService.update(monsterEntity);
    }

    @Override
    public void delete(MonsterDTO monster) {
        Monster monsterEntity = beanMappingService.mapTo(monster, Monster.class);
        monsterService.delete(monsterEntity);
    }

    @Override
    public List<MonsterDTO> findAll() {
        List<Monster> monsters = monsterService.findAll();
        return beanMappingService.mapTo(monsters, MonsterDTO.class);
    }

    @Override
    public MonsterDTO findById(Long id) {
        Monster monster = monsterService.findById(id);
        if (monster == null) {
            return null;
        }
        return beanMappingService.mapTo(monster, MonsterDTO.class);
    }

    @Override
    public MonsterDTO findByName(String name) {
        Monster monster = monsterService.findByName(name);
        if (monster == null) {
            return null;
        }
        return beanMappingService.mapTo(monster, MonsterDTO.class);
    }
}
