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
        if (weapon == null) {
            throw new IllegalArgumentException("weapon is null");
        }
        weaponDao.create(weapon);
        return weaponDao.findById(weapon.getId());
    }

    @Override
    public void update(Weapon weapon) {
        if (weapon == null) {
            throw new IllegalArgumentException("weapon is null");
        }
        weaponDao.update(weapon);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        weaponDao.delete(weaponDao.findById(id));
    }

    @Override
    public List<Weapon> findAll() {
        return weaponDao.findAll();
    }

    @Override
    public Weapon findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return weaponDao.findById(id);
    }

    @Override
    public Weapon findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        return weaponDao.findByName(name);
    }
}
