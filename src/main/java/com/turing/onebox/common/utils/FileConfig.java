package com.turing.onebox.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangYuhan
 * @create 2022-08-24-0:37
 * 专门用于读取配置的属性
 */
@Configuration
@ConfigurationProperties("huang")
public class FileConfig {

    @ConfigurationProperties("file.upload")
    public String getPath() {
        return path;
    }

    private String path;
}
