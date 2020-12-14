package com.unifacef.tcc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

@Component
public class ApplicationUtil {
  private static ApplicationContext staticApplicationContext;

  @Autowired
  private ApplicationContext applicationContext;

  public static String getVersion() {
    try {
      Map<String, Object> beans =
          ApplicationUtil.staticApplicationContext.getBeansWithAnnotation(SpringBootApplication.class);

      String version = (beans == null || beans.isEmpty()) ?
          null : Arrays.asList(beans.values()).get(0).getClass().getPackage().getImplementationVersion();

      if (version == null) {
        return ApplicationUtil.class.getPackage().getImplementationVersion();
      }
    } catch (Throwable t) {
      System.err.println("Unable to get application version: " + t.toString());
    }

    return null;
  }

  @PostConstruct
  public void init() {
    ApplicationUtil.staticApplicationContext = this.applicationContext;
  }
}
