package com.blackteam.pipboy.service.facade;

import com.blackteam.pipboy.api.dto.WeaponDTO;
import com.blackteam.pipboy.api.facade.WeaponFacade;
import com.blackteam.pipboy.persistence.entity.Weapon;
import com.blackteam.pipboy.service.BeanMappingService;
import com.blackteam.pipboy.service.WeaponService;
import enums.StatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class WeaponFacadeImpl implements WeaponFacade {

    @Inject
    private WeaponService weaponService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(WeaponDTO weaponDTO) {
        Weapon weapon = beanMappingService.mapTo(weaponDTO,Weapon.class);
        Set<StatType> statuses = weaponDTO.getStatus();
        weapon.setStatus(statuses);
        return weaponService.create(weapon).getId();
    }

    @Override
    public void update(WeaponDTO weaponDTO) {
        Weapon weapon = beanMappingService.mapTo(weaponDTO,Weapon.class);
        weaponService.update(weapon);
    }

    @Override
    public List<WeaponDTO> findAll() {
        return beanMappingService.mapTo(weaponService.findAll(),WeaponDTO.class);
    }

    @Override
    public WeaponDTO findById(Long id) {
        return beanMappingService.mapTo(weaponService.findById(id),WeaponDTO.class);
    }

    @Override
    public WeaponDTO findByName(String name) {
        return beanMappingService.mapTo(weaponService.findByName(name),WeaponDTO.class);
    }
}
