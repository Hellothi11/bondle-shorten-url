package com.bondle.shortenurl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author nguyen.tam.thi at 10:51 AM 4/2/21
 */
@Configuration
@PropertySource(value = "classpath:custom.yaml", factory = YamlPropertySourceFactory.class)
public class PropertiesConfig {

  @Autowired
  private Environment env;

  public String getConfigValue(String configKey) {
    return env.getProperty(configKey);
  }
}
