package com.blackteam.pipboy.api.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Area DTO
 *
 * @author Jakub Havrila
 */
public class AreaDTO {

    private Long id;
    private String name;
    private Set<MonsterDTO> monsters = new HashSet<>();
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Set<MonsterDTO> getMonsters() {
        return monsters;
    }

    public void setMonsters(Set<MonsterDTO> monsters) {
        this.monsters = monsters;
    }

    public void addMonster(MonsterDTO monster) {
        this.monsters.add(monster);
    }

    public void removeMonster(MonsterDTO monster) {
        this.monsters.remove(monster);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaDTO)) return false;
        AreaDTO areaDTO = (AreaDTO) o;
        return Objects.equals(getName(), areaDTO.getName()) &&
                Objects.equals(getDescription(), areaDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public String toString() {
        return "AreaDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monsters=" + monsters +
                ", description='" + description + '\'' +
                '}';
    }
}

