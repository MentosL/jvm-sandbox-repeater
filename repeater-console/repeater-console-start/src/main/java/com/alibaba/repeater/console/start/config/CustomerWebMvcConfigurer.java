package com.alibaba.repeater.console.start.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
public class CustomerWebMvcConfigurer extends WebMvcConfigurerAdapter {

        @Autowired
        private final ResourceLoader resourceLoader = new DefaultResourceLoader();

        @Bean
        public VelocityConfig velocityConfig() {
            VelocityConfigurer cfg = new VelocityConfigurer();
            cfg.setResourceLoader(resourceLoader);
            cfg.setResourceLoaderPath("classpath:/velocity/templates");
            Properties velocityProperties = new Properties();
            velocityProperties.put("output.encoding", "UTF-8");
            velocityProperties.put("input.encoding", "UTF-8");
            velocityProperties.put("contentType", "text/html;charset=UTF-8");
            cfg.setVelocityProperties(velocityProperties);
            return cfg;
        }

        @Bean
        public ViewResolver viewResolver() {
            VelocityViewResolver resolver = new VelocityViewResolver();
            resolver.setViewClass(VelocityToolboxView.class);
            resolver.setPrefix("/velocity/templates");
            resolver.setSuffix(".vm");
            return resolver;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{
            "classpath:/META-INF/resources/", "classpath:/resources/",
                    "classpath:/static/", "classpath:/public/","classpath:/velocity/" };
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }

}
