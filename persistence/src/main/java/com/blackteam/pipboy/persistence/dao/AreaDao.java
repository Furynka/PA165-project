package com.blackteam.pipboy.persistence.dao;

import com.blackteam.pipboy.persistence.entity.Area;

import java.util.List;

/**
 * Interface for Area dao
 *
 * @author Jakub Havrila
 */
public interface AreaDao {
    /**
     * Find Area in database
     * @param id unique id of Area
     * @return Area with given id
     */
    public Area findById(Long id);

    /**
     * Add Area to database
     * @param a Area to be added
     */
    public void create(Area a);

    /**
     * Update Area
     * @param a Area to be updated
     */
    public void update(Area a);

    /**
     * Delete Area from database
     * @param a Area to be deleted
     */
    public void delete(Area a);

    /**
     * Find all Area in database
     * @return List of Areas
     */
    public List<Area> findAll();

    /**
     * Find Area with given name
     * @param name name of the Area to be found
     * @return Area with given name
     */
    public Area findByName(String name);
}
