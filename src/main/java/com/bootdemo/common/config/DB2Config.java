package com.bootdemo.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

@Configuration
@MapperScan(basePackages ="com.**.mapper2*", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DB2Config {
	@Autowired
	private MyMetaObjectHandler myMetaObjectHandler;
    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev","qa"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }


    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2" )
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("db2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    /**
     * 动态数据源配置
     * @return
     */
    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(db2());
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com.bootdemo.**.mapper2.*.*Mapper.xml"));
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() //添加分页功能
        });
        sqlSessionFactory.setTypeAliasesPackage("com.bootdemo.**");
        //全局策略配置
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public GlobalConfig globalConfiguration() {
    	GlobalConfig conf = new GlobalConfig();
    	DbConfig dbConfig=new DbConfig();
    	//
    	dbConfig.setColumnUnderline(false);
    	//全局默认主键类型
    	dbConfig.setIdType(IdType.UUID);
    	//字段验证策略
    	dbConfig.setFieldStrategy(FieldStrategy.NOT_EMPTY);
    	//是否开启大写命名，默认不开启。
    	dbConfig.setCapitalMode(false);
    	conf.setDbConfig(dbConfig);
        conf.setRefresh(true);
        conf.setMetaObjectHandler(myMetaObjectHandler);
        return conf;
    }
}
