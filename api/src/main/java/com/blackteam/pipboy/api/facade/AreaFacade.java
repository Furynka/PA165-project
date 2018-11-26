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
     * @return Id of area
     */
    Long create(AreaDTO areaDTO);

    /**
     * update name of area
     * @param id id of area
     * @param name name to update
     */
    void updateName(Long id, String name);

    /**
     * update description of area
     * @param id id of area
     * @param desc description to update
     */
    void updateDescription(Long id, String desc);

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
