package com.minlia.rocket.data.generator.autoconfigure;


import com.minlia.rocket.data.generator.service.CodeGenerationService;
import com.minlia.rocket.data.generator.service.CodeGenerationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.minlia.rocket.data.generator"})
public class DataCodeGenerationAutoConfiguration {

  @Bean
  public CodeGenerationService codeGenerationService() {
    return new CodeGenerationServiceImpl();
  }
}
