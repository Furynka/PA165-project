package com.blackteam.pipboy.api.dto;

import lombok.Data;

/**
 * DTO (data transfer object) for finding Person by email.
 *
 * @author Jiri Oliva
 */
@Data
public class PersonFindByEmailDTO {
  String email;
}
