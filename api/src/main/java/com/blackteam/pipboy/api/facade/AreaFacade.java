package com.blackteam.pipboy.api.facade;

import com.blackteam.pipboy.api.dto.AreaDTO;

import java.util.List;

public interface AreaFacade {
    Long create(AreaDTO areaDTO);
    void updateName(Long id, String name);
    void updateDescription(Long id, String desc);
    void delete(Long id);
    AreaDTO findById(Long id);
    AreaDTO findByName(String name);
    List<AreaDTO> findAll();
}
