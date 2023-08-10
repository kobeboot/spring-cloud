package com.springboot.cloud.local;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本地文件 配置信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "local")
public class LocalFileProperties {

    /**
     * 是否开启
     */
    private boolean enable;

    /**
     * 默认路径
     */
    private String basePath;

}
