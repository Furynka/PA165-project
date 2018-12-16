package com.blackteam.pipboy.api.dto;

import lombok.Data;

/**
 * DTO (data transfer object) for Person update info.
 *
 * @author Jiri Oliva
 */
@Data
public class PersonUpdateDTO {
  String name;
  String surname;
  String email;
  boolean administrator;
}
