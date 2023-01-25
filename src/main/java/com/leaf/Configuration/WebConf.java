package com.leaf.Configuration;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConf implements WebMvcConfigurer {
    @Bean
    public VelocityEngine velocityEngine() {
        // les properties ainsi leurs valeurs doivent etre externaliser
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(VelocityRuntimeConstants.RESOURCE_LOADER, "class,file");
        velocityEngine.setProperty(VelocityRuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
        velocityEngine.setProperty(VelocityRuntimeConstants.RUNTIME_LOG_LOGSYSTEM_LOG4J_LOGGER, "VELLOGGER");
        velocityEngine.setProperty(VelocityRuntimeConstants.CLASS_RESOURCE_LOADER_CLASS, "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        return velocityEngine;
    }
}
