package com.blackteam.pipboy.api.facade;

import com.blackteam.pipboy.api.dto.WeaponCreateDTO;
import com.blackteam.pipboy.api.dto.WeaponDTO;

import java.util.List;

/**
 * @author Jan Michalov
 */
public interface WeaponFacade {
    /**
     * Creates a weapon.
     * @param weapon weapon weapon to be created
     */
    Long create(WeaponCreateDTO weapon);

    /**
     * Updates Weapon entity.
     * @param weapon Weapon entity
     */
    void update(WeaponDTO weapon);

    /**
     * Deletes Weapon from database
     * @param id id of a weapon
     */
    void delete(Long id);

    /**
     * Returns all Weapon entities.
     * @return List of Weapon entities.
     */
    List<WeaponDTO> findAll();

    /**
     * Finds Weapon entity by its id.
     * @param id Weapon entity id
     * @return Weapon entity or null.
     */
    WeaponDTO findById(Long id);

    /**
     * Finds Weapons entity by its name.
     * @param name Weapon entity name
     * @return Weapon entity or null.
     */
    WeaponDTO findByName(String name);
}
