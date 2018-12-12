package com.blackteam.pipboy.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Entity not found.")
public class NotFoundException extends RuntimeException {}
