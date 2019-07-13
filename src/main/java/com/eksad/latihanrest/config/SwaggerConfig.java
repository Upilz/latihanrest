package com.eksad.latihanrest.config;

import static springfox.documentation.builders.PathSelectors.regex; //manually

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	@Bean
	public Docket eksadrestAPI()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.latihanrest"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo())
				.tags(
					new Tag("Brand", " Supermarket Brand's  Management API"),
					new Tag("Product", "Supermarket Product's Management API"),
					new Tag("Supermarket's Data Manipulation API", "Insert-Update-Delete API")
					);
	}
	
	private ApiInfo metaInfo()
	{
		ApiInfo apiInfo = new ApiInfo(
				"Supermarket Data Management REST API",
				"REST API Collection for Supermarket Data Management",
				"1.0.0",
				"http://google.com",
				new Contact("Ufi Mufidah", "https://github.com/Upilz/", "mufidahufi26@gmail.com"),
				"Ufi 1.0",
				"http://www.google.com/license",
				Collections.emptyList()); //vendor ext
		
		return apiInfo;
	}

}
