package com.blackteam.pipboy.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="Entity already exists.")
public class EntityAlreadyExistsException extends RuntimeException {}
