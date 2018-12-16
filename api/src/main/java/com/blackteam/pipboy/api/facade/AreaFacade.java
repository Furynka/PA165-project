package com.blackteam.pipboy.api.facade;

import com.blackteam.pipboy.api.dto.AreaDTO;

import java.util.List;

/**
 * Area Facade interface
 *
 * @author Jakub Havrila
 */
public interface AreaFacade {
    /**
     * Create area
     * @param areaDTO areaDTO
     */
    void create(AreaDTO areaDTO);

    /**
     * update name of area
     * @param areaDTO update
     */
    void update(AreaDTO areaDTO);

    /**
     * delete area
     * @param id id of area
     */
    void delete(Long id);

    /**
     * Find area by ID
     * @param id id of area
     * @return area
     */
    AreaDTO findById(Long id);

    /**
     * Find area by name
     * @param name name of area
     * @return area
     */
    AreaDTO findByName(String name);

    /**
     * Find all areas
     * @return list of all areas
     */
    List<AreaDTO> findAll();
}
