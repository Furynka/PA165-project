package com.blackteam.pipboy.api.dto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Michalov
 */
public class MonsterUpdateDTO {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    private String name;

    @NotNull
    private Integer height;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer power;

    @NotNull
    private Integer agility;

    @NotNull
    private Integer speed;

    private Long areaId;

    private List<Long> effectiveWeaponsIds = new ArrayList<>();

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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long area) {
        this.areaId = area;
    }

    public List<Long> getEffectiveWeaponsIds() {
        return effectiveWeaponsIds;
    }

    public void setEffectiveWeaponsIds(List<Long> effectiveWeaponsIds) {
        this.effectiveWeaponsIds = effectiveWeaponsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonsterUpdateDTO)) return false;

        MonsterUpdateDTO that = (MonsterUpdateDTO) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MonsterUpdateDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", power=").append(power);
        sb.append(", agility=").append(agility);
        sb.append(", speed=").append(speed);
        sb.append(", areaId=").append(areaId);
        sb.append(", effectiveWeaponsIds=").append(effectiveWeaponsIds);
        sb.append('}');
        return sb.toString();
    }
}
