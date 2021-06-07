package io.torres.ddunddun.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile({"dev","local"})
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 각 requestMethod 타입에 따른 메시지 타입 , globalResponseMessage() 메소드를 통해 HTTP 메소드의 전역적 오버라이딩 응답메세지
                .globalResponseMessage(RequestMethod.POST, responseMessage())
                .globalResponseMessage(RequestMethod.GET, responseMessage())
                .globalResponseMessage(RequestMethod.PUT, responseMessage())
                .globalResponseMessage(RequestMethod.DELETE, responseMessage())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.torres.ddunddun.controller"))  // 컨트롤러 패키지명으로 해당 패키지의 requsetmapping만 가져 올수 있다
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Lists.newArrayList(securityContext())) // // JWT 세팅
                .securitySchemes(Arrays.asList(apiKey())) // JWT 키값 넣는곳
                .apiInfo(apiInfo());  // swagger 커스텀 문서의 정보
    }

    private List<ResponseMessage> responseMessage() {
        return ImmutableList.of(
                new ResponseMessageBuilder().code(401).message("인증오류").build(),
                new ResponseMessageBuilder().code(500).message("서버오류").build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kakaowork Platform Service API Document")
                .description("Kakaowork Platform API based on Spring Boot")
                .version("1.0")
                .contact(new Contact("torres", "", "kinderforyou@kakao.com"))
                .build();
    }

    private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
        return springfox.documentation.spi.service.contexts.SecurityContext
                .builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer <Access Token>", "Authorization", "header");
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("Bearer <Access Token>", authorizationScopes));
    }
}
