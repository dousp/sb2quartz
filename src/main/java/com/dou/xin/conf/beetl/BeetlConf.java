package com.dou.xin.conf.beetl;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * beetl springboot 集成
 */
@Configuration
public class BeetlConf {

    @Autowired
    BeetlProperties beetlProperties;


    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConf getBeetlConfiguration() {
        BeetlGroupUtilConf beetlGroupUtilConf = new BeetlGroupUtilConf();
        try {
            //获取Spring Boot 的ClassLoader
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if(loader==null){
                // 也可以直接取  .class.getClassLoader()
                loader = BeetlConf.class.getClassLoader();
            }
            ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, beetlProperties.getTemplatesPath());
            beetlGroupUtilConf.setResourceLoader(cploder);
            beetlGroupUtilConf.setConfigProperties(beetlProperties.getProperties());
            return beetlGroupUtilConf;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(
            @Qualifier("beetlConfig") BeetlGroupUtilConf beetlGroupUtilConf) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        // controller中指定对应后缀，方便追溯与使用不同后缀的模板
        // beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConf);
        return beetlSpringViewResolver;
    }

    @Bean(name = "groupTemplate")
    public GroupTemplate getGt(@Qualifier("beetlConfig") BeetlGroupUtilConf beetlGroupUtilConf){
        return beetlGroupUtilConf.getGroupTemplate();
    }

    /**
     * 备用
     */
    // @Bean(initMethod = "init", name = "beetlConfig")
    // public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
    //
    //     BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
    //     ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
    //     try {
    //         String root =  patternResolver.getResource("classpath:"+beetlProperties.getTemplatesPath()).getFile().toString();
    //         WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(root);
    //         beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
    //         beetlGroupUtilConfiguration.setConfigProperties(beetlProperties.getProperties());
    //         // beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
    //         return beetlGroupUtilConfiguration;
    //     } catch (Exception e) {
    //         throw new RuntimeException(e);
    //     }
    // }


    // @Bean(name = "beetlSqlScannerConfigurer")
    // public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
    //     BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
    //     conf.setBasePackage("boottest");
    //     conf.setDaoSuffix("Dao");
    //     conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
    //     return conf;
    // }
    //
    // @Bean(name = "sqlManagerFactoryBean")
    // @Primary
    // public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource) {
    //     SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
    //
    //     BeetlSqlDataSource  source = new BeetlSqlDataSource();
    //     source.setMasterSource(datasource);;
    //     factory.setCs(source);
    //     factory.setDbStyle(new MySqlStyle());
    //     factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
    //     factory.setNc(new UnderlinedNameConversion());
    //     factory.setSqlLoader(new ClasspathLoader("/sql"));
    //     return factory;
    // }
    //
    //
    // @Bean(name="datasource")
    // public DataSource getDataSource() {
    //     System.out.println("-------------------- primaryDataSource init ---------------------");
    //     return DataSourceBuilder.create().url("jdbc:mysql://127.0.0.1/test").username("root").password("lijzh780214").build();
    // }
    //
    //
    // @Bean(name="txManager")
    // public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DataSource datasource) {
    //     DataSourceTransactionManager dsm = new DataSourceTransactionManager();
    //     dsm.setDataSource(datasource);
    //     return dsm;
    // }



}
