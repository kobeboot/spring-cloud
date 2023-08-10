package com.springboot.cloud.config;

import com.springboot.cloud.support.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 * 禁用方法1：使用注解@Profile({"dev","test"})
 * 表示在开发或测试环境开启，而在生产关闭。（推荐使用） 禁用方法2：使用注解@ConditionalOnProperty(name = "swagger.enable",
 * havingValue = "true") 然后在测试配置或者开发配置中添加swagger.enable=true即可开启，生产环境不填则默认关闭Swagger.
 *
 * @author lengleng
 */
@RequiredArgsConstructor
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class OpenAPIDefinition extends OpenAPI implements InitializingBean, ApplicationContextAware {

	@Setter
	private String path;

	private ApplicationContext applicationContext;

	private SecurityScheme securityScheme(SwaggerProperties swaggerProperties) {
		OAuthFlow clientCredential = new OAuthFlow();
		clientCredential.setTokenUrl(swaggerProperties.getTokenUrl());
		clientCredential.setScopes(new Scopes().addString(swaggerProperties.getScope(), swaggerProperties.getScope()));
		OAuthFlows oauthFlows = new OAuthFlows();
		oauthFlows.password(clientCredential);
		SecurityScheme securityScheme = new SecurityScheme();
		securityScheme.setType(SecurityScheme.Type.OAUTH2);
		securityScheme.setFlows(oauthFlows);
		return securityScheme;
	}

	@Override
	public void afterPropertiesSet() {
		SwaggerProperties swaggerProperties = applicationContext.getBean(SwaggerProperties.class);
		this.info(new Info().title(swaggerProperties.getTitle()));
		// oauth2.0 password
		this.schemaRequirement(HttpHeaders.AUTHORIZATION, this.securityScheme(swaggerProperties));
		// servers
		List<Server> serverList = new ArrayList<>();
		serverList.add(new Server().url(swaggerProperties.getGateway() + "/" + path));
		this.servers(serverList);
		// 支持参数平铺
		SpringDocUtils.getConfig().addSimpleTypesForParameterObject(Class.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
