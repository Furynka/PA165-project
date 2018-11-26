package com.blackteam.pipboy.api.dto;

import javax.validation.constraints.NotNull;
import enums.StatType;

import java.util.HashSet;
import java.util.Set;

public class WeaponCreateDTO {

    @NotNull
    private String name;

    private String description;

    private Set<StatType> status = new HashSet<>();

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
}
