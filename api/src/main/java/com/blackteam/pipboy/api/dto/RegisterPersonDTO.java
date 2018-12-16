package com.blackteam.pipboy.api.dto;


import lombok.Data;

/**
 * DTO (data transfer object) for registering new Person.
 *
 * @author Jiri Oliva
 */
@Data
public class RegisterPersonDTO {
  private String name;
  private String surname;
  private String email;
  private String password;
  private String password2;
}
