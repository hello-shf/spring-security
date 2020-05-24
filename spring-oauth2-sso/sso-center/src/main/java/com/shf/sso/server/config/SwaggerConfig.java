package com.shf.sso.server.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/4/19 14:58
 * @Version V1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(SwaggerConfig.basePackage("com.shf.sso.server,org.springframework.security.oauth2.provider.endpoint"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("脚手架接口文档")
                .description("提供一站式sso解决方案")
                .contact("shf")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();

    }
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return new Predicate<RequestHandler>() {

            @Override
            public boolean apply(RequestHandler input) {
                return declaringClass(input).transform(handlerPackage(basePackage)).or(true);
            }
        };
    }
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return new Function<Class<?>, Boolean>() {

            @Override
            public Boolean apply(Class<?> input) {
                for (String strPackage : basePackage.split(",")) {
                    boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                    if (isMatch) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
