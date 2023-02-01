package com.leaf.configuration;

import com.leaf.propertiesConf.VelocityEngineProperties;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/velocity/velocity-engine.properties")
public class VelocityConf {

    @Autowired
    private VelocityEngineProperties vep;

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(VelocityRuntimeConstants.RESOURCE_LOADER, vep.getResourceLoader());
        velocityEngine.setProperty(VelocityRuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, vep.getRuntimeLogSystemClass());
        velocityEngine.setProperty(VelocityRuntimeConstants.RUNTIME_LOG_LOGSYSTEM_LOG4J_LOGGER, vep.getRuntimeLogSystemLog4jLogger());
        velocityEngine.setProperty(VelocityRuntimeConstants.CLASS_RESOURCE_LOADER_CLASS, vep.getResourceLoaderClass());
        velocityEngine.init();
        return velocityEngine;
    }
}
