package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.entity.Area;
import java.util.List;

/**
 * Area service layer interface
 */
public interface AreaService {
    Area create(Area area) throws IllegalArgumentException;
    void update(Area area) throws IllegalArgumentException;
    void remove(Area area) throws IllegalArgumentException;
    Area findById(Long id) throws IllegalArgumentException;
    Area findByName(String name) throws IllegalArgumentException;
    List<Area> findAll();
}
