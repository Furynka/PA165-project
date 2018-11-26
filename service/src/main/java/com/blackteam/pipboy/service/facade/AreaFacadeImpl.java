package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.AreaDTO;
import com.blackteam.pipboy.api.facade.AreaFacade;
import com.blackteam.pipboy.persistence.entity.Area;
import com.blackteam.pipboy.persistence.entity.Monster;
import com.blackteam.pipboy.service.AreaService;
import com.blackteam.pipboy.service.BeanMappingService;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Implementation of AreaFacade
 *
 * @author Jakub Havrila
 */
@Service
@Transactional
public class AreaFacadeImpl implements AreaFacade {

    @Inject
    private AreaService areaService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(AreaDTO areaDTO) {
        Area area = beanMappingService.mapTo(areaDTO, Area.class);
        Area createArea = areaService.create(area);
        return createArea.getId();
    }

    @Override
    public void updateName(Long id, String name) {
        Area area = areaService.findById(id);
        area.setName(name);
        areaService.update(area);
    }

    @Override
    public void updateDescription(Long id, String desc) {
        Area area = areaService.findById(id);
        area.setDescription(desc);
        areaService.update(area);
    }


    @Override
    public void delete(Long id) {
        Area area = areaService.findById(id);
        areaService.remove(area);
    }

    @Override
    public AreaDTO findById(Long id) {
        Area area = areaService.findById(id);
        return beanMappingService.mapTo(area, AreaDTO.class);
    }

    @Override
    public AreaDTO findByName(String name) {
        Area area = areaService.findByName(name);
        return beanMappingService.mapTo(area, AreaDTO.class);
    }

    @Override
    public List<AreaDTO> findAll() {
        List<Area> areas = areaService.findAll();
        return beanMappingService.mapTo(areas, AreaDTO.class);
    }
}
