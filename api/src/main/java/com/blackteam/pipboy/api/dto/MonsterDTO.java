package com.blackteam.pipboy.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Monster DTO
 *
 * @author  Jiří Čechák
 * @since 2018-11-25
 */
public class MonsterDTO {

    private Long id;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer power;
    private Integer agility;
    private Integer speed;
//    private AreaDTO area;
//    private List<WeaponDTO> effectiveWeapons = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

//    public AreaDTO getArea() {
//        return area;
//    }
//
//    public void setArea(AreaDTO area) {
//        this.area = area;
//    }
//
//    public List<WeaponDTO> getEffectiveWeapons() {
//        return effectiveWeapons;
//    }
//
//    public void setEffectiveWeapons(List<WeaponDTO> effectiveWeapons) {
//        this.effectiveWeapons = effectiveWeapons;
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof MonsterDTO))
            return false;
        MonsterDTO other = (MonsterDTO) obj;
        if (name == null) {
            return other.getName() == null;
        } else return name.equals(other.getName());
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", power=" + power +
                ", agility=" + agility +
                ", speed=" + speed +
//                ", area=" + area +
//                ", effectiveWeapons=" + effectiveWeapons +
                '}';
    }
}
