package com.leaf.propertiesConf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "velocity.engine")
public class VelocityEngineProperties {

    private String resourceLoader = "class,file";

    private String runtimeLogSystemClass = "org.apache.velocity.runtime.log.Log4JLogChute";

    private String runtimeLogSystemLog4jLogger = "VELLOGGER";

    private String resourceLoaderClass = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    public String getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(String resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getRuntimeLogSystemClass() {
        return runtimeLogSystemClass;
    }

    public void setRuntimeLogSystemClass(String runtimeLogSystemClass) {
        this.runtimeLogSystemClass = runtimeLogSystemClass;
    }

    public String getRuntimeLogSystemLog4jLogger() {
        return runtimeLogSystemLog4jLogger;
    }

    public void setRuntimeLogSystemLog4jLogger(String runtimeLogSystemLog4jLogger) {
        this.runtimeLogSystemLog4jLogger = runtimeLogSystemLog4jLogger;
    }

    public String getResourceLoaderClass() {
        return resourceLoaderClass;
    }

    public void setResourceLoaderClass(String resourceLoaderClass) {
        this.resourceLoaderClass = resourceLoaderClass;
    }
}
