package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.MonsterDao;
import com.blackteam.pipboy.persistence.entity.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Monster service implementation
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private MonsterDao monsterDao;

    @Override
    public Monster create(Monster monster) {
        monsterDao.create(monster);
        return null;
    }

    @Override
    public Monster update(Monster monster) {
        monsterDao.update(monster);
        return null;
    }

    @Override
    public void delete(Monster monster) {
        monsterDao.delete(monster);
    }

    @Override
    public List<Monster> findAll() {
        return monsterDao.findAll();
    }

    @Override
    public Monster findById(Long id) {
        return monsterDao.findById(id);
    }

    @Override
    public Monster findByName(String name) {
        return monsterDao.findByName(name);
    }

    @Override
    public List<Monster> findAllMonstersFromSameArea(Monster monster) {
        List<Monster> monstersFromSameArea = new ArrayList<>();

        if (monster.getArea() != null) {
            List<Monster> monsters = monsterDao.findAll();

            for (Monster m : monsters) {
                if (m.getArea() != null && m.getArea() == monster.getArea()) {
                    monstersFromSameArea.add(m);
                }
            }
        }

        return monstersFromSameArea;
    }

    @Override
    public Monster findTheStrongestMonster() {
        Monster strongestMonster = null;

        List<Monster> monsters = monsterDao.findAll();

        for (Monster m : monsters) {
            if (strongestMonster == null ||
                    strongestMonster.getPower() + strongestMonster.getAgility() + strongestMonster.getSpeed() <
                            m.getPower() + m.getAgility() + m.getSpeed()) {
                strongestMonster = m;
            }
        }

        return strongestMonster;
    }
}
