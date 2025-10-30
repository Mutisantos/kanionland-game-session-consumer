package com.kanionland.game.session.consumer.infrastructure.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

  private static final String RULES_PATH = "rules/game-session-rules.drl";
  private final KieServices kieServices = KieServices.Factory.get();

  @Bean
  public KieContainer kieContainer() {
    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH, "UTF-8"));

    KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
    kb.buildAll();

    if (kb.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
      throw new RuntimeException("Error building DRL files: " + kb.getResults().toString());
    }

    KieModule kieModule = kb.getKieModule();
    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

  @Bean
  public KieSession kieSession() {
    return kieContainer().newKieSession();
  }
}