package com.assetManage.tusdt.base.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 * Author: xxw
 * Date: 2020-01-16
 * Time: 16:40
 */

@Configuration
@EnableSwagger2
public class Swagger2App {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.assetManage"))
                .paths(PathSelectors.any())
                .build();
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("AssetManage 学院资产管理系统")
                //创建人
//                .contact(new Contact("熊小尾", "", "65238004@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述 学院资产管理系统")
                .build();
    }
}

