package com.cqcj.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("重庆财经")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cqcj"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact("林鸿涛","","1165486015@qq.com");
        return new ApiInfo(
                "点餐系统",
                "重庆财经点餐系统",
                "1.0",
                "https://swagger.io/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}