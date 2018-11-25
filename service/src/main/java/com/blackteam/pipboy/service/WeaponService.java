package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Weapon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeaponService {
    /**
     * Creates and persists Weapon entity.
     * @param weapon Weapon entity
     */
    Weapon create(Weapon weapon);

    /**
     * Updates Weapon entity.
     * @param weapon Weapon entity
     */
    void update(Weapon weapon);

    /**
     * Returns all Weapon entities.
     * @return List of Weapon entities.
     */
    List<Weapon> findAll();

    /**
     * Finds Weapon entity by its id.
     * @param id Weapon entity id
     * @return Weapon entity or null.
     */
    Weapon findById(Long id);

    /**
     * Finds Weapons entity by its name.
     * @param name Weapon entity name
     * @return Weapon entity or null.
     */
    Weapon findByName(String name);
}
