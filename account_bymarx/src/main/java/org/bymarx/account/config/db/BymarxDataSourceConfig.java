package org.bymarx.account.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = BymarxDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "bymarxSqlSessionFactory")
public class BymarxDataSourceConfig {

    // 精确到 bymarx 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.bymarx.account.dao.wordpress.bymarx";
    static final String MAPPER_LOCATION = "classpath:mapper/wordpress/bymarx/*.xml";

    @Value("${bymarx.datasource.url}")
    private String url;

    @Value("${bymarx.datasource.username}")
    private String user;

    @Value("${bymarx.datasource.password}")
    private String password;

    @Value("${bymarx.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "bymarxDataSource")
    @Primary
    public DataSource bymarxDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "bymarxTransactionManager")
    @Primary
    public DataSourceTransactionManager bymarxTransactionManager() {
        return new DataSourceTransactionManager(bymarxDataSource());
    }

    @Bean(name = "bymarxSqlSessionFactory")
    @Primary
    public SqlSessionFactory bymarxSqlSessionFactory(@Qualifier("bymarxDataSource") DataSource bymarxDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(bymarxDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(BymarxDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}