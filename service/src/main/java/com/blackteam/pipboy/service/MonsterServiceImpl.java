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
    public Monster create(Monster monster) throws IllegalArgumentException {
        if (monster == null) {
            throw new IllegalArgumentException("monster is null");
        }
        monsterDao.create(monster);
        System.out.println(monster);
        return monster;
    }

    @Override
    public void update(Monster monster) throws IllegalArgumentException {
        if (monster == null) {
            throw new IllegalArgumentException("monster is null");
        }
        monsterDao.update(monster);
    }

    @Override
    public void delete(Monster monster) throws IllegalArgumentException {
        if (monster == null) {
            throw new IllegalArgumentException("monster is null");
        }
        monsterDao.delete(monster);
    }

    @Override
    public List<Monster> findAll() {
        return monsterDao.findAll();
    }

    @Override
    public Monster findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        return monsterDao.findById(id);
    }

    @Override
    public Monster findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        return monsterDao.findByName(name);
    }

    @Override
    public List<Monster> findAllMonstersFromSameArea(Monster monster) throws IllegalArgumentException {
        if (monster == null) {
            throw new IllegalArgumentException("monster is null");
        }

        List<Monster> monstersFromSameArea = new ArrayList<>();
        Monster dbMonster = findById(monster.getId());

        if (dbMonster.getArea() != null) {
            List<Monster> monsters = monsterDao.findAll();

            for (Monster m : monsters) {
                if (m.getArea() != null && m.getArea().equals(dbMonster.getArea())) {
                    monstersFromSameArea.add(m);
                }
            }
        }

        return monstersFromSameArea;
    }

    @Override
    public Monster findTheStrongestMonster() throws IllegalArgumentException {
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