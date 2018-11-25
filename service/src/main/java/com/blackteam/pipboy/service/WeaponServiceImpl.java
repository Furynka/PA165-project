package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.WeaponDao;
import com.blackteam.pipboy.persistence.entity.Weapon;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.*;

@Service
public class WeaponServiceImpl implements WeaponService{
    @Inject
    private WeaponDao weaponDao;

    @Override
    public Weapon create(Weapon weapon) {
        weaponDao.create(weapon);
        return weaponDao.findById(weapon.getId());
    }

    @Override
    public void update(Weapon weapon) {
        weaponDao.update(weapon);
    }

    @Override
    public List<Weapon> findAll() {
        return weaponDao.findAll();
    }

    @Override
    public Weapon findById(Long id) {
        return weaponDao.findById(id);
    }

    @Override
    public Weapon findByName(String name) {
        return weaponDao.findByName(name);
    }
}
