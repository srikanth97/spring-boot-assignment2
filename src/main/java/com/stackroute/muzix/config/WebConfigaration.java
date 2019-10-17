package com.stackroute.muzix.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConfigurationProperties("spring.datasource")
public class WebConfigaration {

    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    @Bean
    ServletRegistrationBean h2ServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    @Profile("dev")
    @Bean
    public String devDataBaseConnection(){
        System.out.println("DB connection for dev-h2");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for dev-h2";
    }

    @Profile("prod")
    @Bean
    public String prodDataBaseConnection(){
        System.out.println("DB connection for prod-mysql");
        System.out.println(driverClassName);
        System.out.println(url);
        System.out.println(userName);
        return "DB connection for prod-mysql";
    }


}
