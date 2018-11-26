package com.blackteam.pipboy.service.config;

import com.blackteam.pipboy.persistence.AppContext;
import com.blackteam.pipboy.service.WeaponServiceImpl;
import com.blackteam.pipboy.service.facade.AreaFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Service configuration
 *
 * @author Jiri Oliva
 */
@Configuration
@Import(AppContext.class)
@ComponentScan(basePackageClasses = {WeaponServiceImpl.class, AreaFacadeImpl.class})
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
