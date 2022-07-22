package com.example.springvelocity;

import com.shieldsolutions.velocity.view.VelocityConfigurer;
import com.shieldsolutions.velocity.view.VelocityViewResolver;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.FreeMarkerConfigurerBeanDefinitionParser;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@ComponentScan({"com.example.springvelocity"})
public class WebConf implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver(){
        VelocityViewResolver resolver=new VelocityViewResolver();
        resolver.setSuffix(".vm");
        return resolver;
    }
    @Bean
    public VelocityConfigurer configurer(){
        VelocityConfigurer velocityConfigurer=new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("/WEB-INF/pages/");
        return velocityConfigurer;
    }
    @Bean
    public VelocityEngine velocityEngine(){
        VelocityEngine velocityEngine=new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
        velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
        velocityEngine.setProperty("runtime.log.logsystem.log4j.logger", "VELLOGGER");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        velocityEngine.init();
        return velocityEngine;
    }
}
