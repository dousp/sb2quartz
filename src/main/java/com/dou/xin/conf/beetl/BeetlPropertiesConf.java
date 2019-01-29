package com.dou.xin.conf.beetl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在这里以Bean的形式声明不同的Properties对象，方便对初始化做自定义修改
 * @author dsp
 * @see BeetlProperties
 */
@Configuration
public class BeetlPropertiesConf {

    @Bean
    @ConfigurationProperties(prefix = BeetlProperties.BEETLCONF_PREFIX)
    public BeetlProperties beetlSettings(){
        return new BeetlProperties();
    }
}
