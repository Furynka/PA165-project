package com.blackteam.pipboy.rest;

import com.blackteam.pipboy.data.facade.SampleDataFacade;
import com.blackteam.pipboy.service.config.ServiceConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Web configurer
 *
 * @author Jiri Oliva
 */
@EnableWebMvc
@Configuration
@Import({ServiceConfig.class})
@ComponentScan(basePackages = {"com.blackteam.pipboy"})
public class RootWebContext implements WebMvcConfigurer {

  private static final Logger LOG = LogManager.getLogger(RootWebContext.class);

  @Inject
  SampleDataFacade sampleDataFacade;

  @PostConstruct
  public void dataLoading() {
    LOG.debug("dataLoading()");
    sampleDataFacade.loadData();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AllowOriginInterceptor());
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Bean
  @Primary
  public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));
    objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

    jsonConverter.setObjectMapper(objectMapper);
    return jsonConverter;
  }

  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(customJackson2HttpMessageConverter());
  }
}
