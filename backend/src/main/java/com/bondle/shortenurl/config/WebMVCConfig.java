package com.bondle.shortenurl.config;

import com.bondle.shortenurl.util.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author nguyen.tam.thi at 3:22 PM 4/4/21
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

  private static final String SALT = "bondle";

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH");
  }

  @Bean
  Hashids hashids() {
    return new Hashids(SALT);
  }

}
