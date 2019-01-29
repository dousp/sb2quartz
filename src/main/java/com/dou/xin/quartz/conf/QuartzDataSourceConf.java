package com.dou.xin.quartz.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class QuartzDataSourceConf {

    @QuartzDataSource
    @Bean("quartzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.quartz")
    public DataSource quartzDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("quartzJdbcTemplate")
    public JdbcTemplate quartzJdbcTemplate(@Qualifier("quartzDataSource") DataSource quartzDataSource) {
        return new JdbcTemplate(quartzDataSource);
    }
}
