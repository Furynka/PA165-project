package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Area;

import java.util.List;

/**
 * Area service layer interface
 *
 * @author Jakub Havrila
 */
public interface AreaService {
    /**
     * Create area
     * @param area area to be created
     * @return created area
     * @throws IllegalArgumentException if area is null
     */
    Area create(Area area) throws IllegalArgumentException;

    /**
     * Update area
     * @param area area to be updated
     * @throws IllegalArgumentException if area is null
     */
    void update(Area area) throws IllegalArgumentException;

    /**
     * Remove area
     * @param area area to be removed
     * @throws IllegalArgumentException if area is null
     */
    void remove(Area area) throws IllegalArgumentException;

    /**
     * Find area by ID
     * @param id id of area
     * @return area
     * @throws IllegalArgumentException if id is null
     */
    Area findById(Long id) throws IllegalArgumentException;

    /**
     * Find area by name
     * @param name name of the area
     * @return area
     * @throws IllegalArgumentException if name is null
     */
    Area findByName(String name) throws IllegalArgumentException;

    /**
     * Find all areas
     * @return list of all areas
     */
    List<Area> findAll();
}
