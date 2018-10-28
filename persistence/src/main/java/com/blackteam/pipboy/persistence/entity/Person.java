package com.blackteam.pipboy.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity class representing person (user).
 *
 * @author Jiri Oliva
 */
@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false)
  private String surname;

  @NotNull
  @Column(nullable = false, unique = true)
  @Pattern(regexp=".+@.+\\....?")
  private String email;

  @NotNull
  @Column(nullable = false)
  private String password;

  @OneToMany
  private List<Weapon> weapons = new ArrayList<>();

  @NotNull
  private Boolean administrator;

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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getAdministrator() {
    return administrator;
  }

  public void setAdministrator(Boolean administrator) {
    this.administrator = administrator;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (this.id == null) return true;
    if (o instanceof Person) {
      Person person = (Person) o;
      return Objects.equals(id, person.id) &&
              Objects.equals(email, person.email);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email);
  }

  @Override
  public String toString() {
    return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", administrator=" + administrator +
            '}';
  }
}
