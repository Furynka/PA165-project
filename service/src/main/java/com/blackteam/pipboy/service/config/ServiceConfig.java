package com.blackteam.pipboy.service.config;

import com.blackteam.pipboy.persistence.AppContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Service configuration
 *
 * @author Jiri Oliva
 */
@Configuration
@Import(AppContext.class)
public class ServiceConfig {

  @Bean
  public Mapper dozer() {
    DozerBeanMapper dozer = new DozerBeanMapper();
    dozer.addMapping(new AppDozerConfig());
    return dozer;
  }

  public class AppDozerConfig extends BeanMappingBuilder {
    @Override
    protected void configure() {

    }
  }

}
