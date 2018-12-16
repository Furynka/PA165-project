package com.blackteam.pipboy.api.dto;

import lombok.Data;

/**
 * DTO (data transfer object) for changing Person's password.
 *
 * @author Jiri Oliva
 */
@Data
public class PersonChangePasswordDTO {
  long id;
  String password;
}
