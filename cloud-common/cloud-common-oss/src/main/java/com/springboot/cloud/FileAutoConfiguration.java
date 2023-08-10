package com.springboot.cloud;

import com.springboot.cloud.core.FileProperties;
import com.springboot.cloud.local.LocalFileAutoConfiguration;
import com.springboot.cloud.oss.OssAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * aws 自动配置类
 *
 * @author lengleng
 * @author 858695266
 */
@Import({ LocalFileAutoConfiguration.class, OssAutoConfiguration.class })
@EnableConfigurationProperties({ FileProperties.class })
public class FileAutoConfiguration {

}
