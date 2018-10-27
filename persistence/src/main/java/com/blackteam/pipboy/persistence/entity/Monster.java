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
}
