package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ServiceAController {
  
  @Value("${message}")
  private String message;

  @Value("${greeting}")
  private String greeting;
  
  @RequestMapping("/")
  public String getName() {
    return message + ", " + greeting;
  }
}

