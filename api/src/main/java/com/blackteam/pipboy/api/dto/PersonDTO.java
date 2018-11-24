package com.blackteam.pipboy.api.dto;

import lombok.Data;

/**
 * DTO (data transfer object) for Person related info.
 *
 * @author Jiri Oliva
 */
@Data
public class PersonDTO {
  private Long id;
  private String name;
  private String surname;
  private String email;
  private String password;
  private boolean administrator;

}
