package com.blackteam.pipboy.persistence.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Monster entity.
 *
 * @author  Jiří Čechák
 * @since   2018-10-26
 */
@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false,unique=true)
    private String name;

    @NotNull
    @Column(nullable=false)
    private Integer height;

    @NotNull
    @Column(nullable=false)
    private Integer weight;

    @NotNull
    @Column(nullable=false)
    private Integer power;

    @NotNull
    @Column(nullable=false)
    private Integer agility;

    @NotNull
    @Column(nullable=false)
    private Integer speed;

    public Monster() {}

    public Monster(Long id) {
        this.id = id;
    }

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
        if (! (obj instanceof Monster))
            return false;
        Monster other = (Monster) obj;
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
                '}';
    }
}
