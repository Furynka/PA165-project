package com.blackteam.pipboy.service;

import com.blackteam.pipboy.persistence.dao.AreaDao;
import com.blackteam.pipboy.persistence.entity.Area;
import javax.inject.Inject;

import java.util.List;

/**
 * Implementation of AreaService
 *
 * @author Jakub Havrila
 */
public class AreaServiceImpl implements AreaService {

    @Inject
    private AreaDao areaDao;

    @Override
    public Area create(Area area) throws IllegalArgumentException {
        if (area == null) {
            throw new IllegalArgumentException("Area is null.");
        }
        areaDao.create(area);
        return area;
    }

    @Override
    public void update(Area area) throws IllegalArgumentException {
        if (area == null) {
            throw new IllegalArgumentException("Area is null.");
        }
        areaDao.update(area);
    }

    @Override
    public void remove(Area area) throws IllegalArgumentException {
        if (area == null) {
            throw new IllegalArgumentException("Area is null.");
        }
        areaDao.delete(area);
    }

    @Override
    public Area findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return areaDao.findById(id);
    }

    @Override
    public Area findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name is null.");
        }
        return areaDao.findByName(name);
    }

    @Override
    public List<Area> findAll() {
        return areaDao.findAll();
    }
}
