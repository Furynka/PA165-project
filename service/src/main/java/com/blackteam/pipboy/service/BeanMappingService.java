package com.blackteam.pipboy.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Bean mapping interface.
 *
 * @author Jiri Oliva
 */
public interface BeanMappingService {
  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

  <T> T mapTo(Object u, Class<T> mapToClass);

  Mapper getMapper();
}
