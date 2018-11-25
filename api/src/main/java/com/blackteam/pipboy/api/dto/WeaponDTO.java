package com.blackteam.pipboy.api.dto;

import enums.StatType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Weapon DTO
 *
 * @author janmichalov
 */
public class WeaponDTO {
    private Long id;

    private String name;

    private String description;

    private Set<StatType> status = new HashSet<>();

    private List<MonsterDTO> vulnerableMonsters = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StatType> getStatus() {
        return status;
    }

    public void setStatus(Set<StatType> status) {
        this.status = status;
    }

    public List<MonsterDTO> getVulnerableMonsters() {
        return vulnerableMonsters;
    }

    public void setVulnerableMonsters(List<MonsterDTO> vulnerableMonsters) {
        this.vulnerableMonsters = vulnerableMonsters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof WeaponDTO)) return false;

        WeaponDTO weapon = (WeaponDTO) o;

        return getName() != null ? getName().equals(weapon.getName()) : weapon.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weapon{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", status=").append(status);
        sb.append(", vulnerableMonsters=").append(vulnerableMonsters);
        sb.append('}');
        return sb.toString();
    }
}
