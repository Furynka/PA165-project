package com.blackteam.pipboy.api.dto;

import lombok.Data;

/**
 * DTO (data transfer object) for Person's login attempt.
 *
 * @author Jiri Oliva
 */
@Data
public class PersonLoginDTO {
  private String email;
  private String password;
  private String newPassword;
}
