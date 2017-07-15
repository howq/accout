package org.bymarx.account.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = NewsDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "newsSqlSessionFactory")
public class NewsDataSourceConfig {

    // 精确到 news 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.bymarx.account.dao.wordpress.xinminnews";
    static final String MAPPER_LOCATION = "classpath:mapper/wordpress/xinminnews/*.xml";

    @Value("${news.datasource.url}")
    private String url;

    @Value("${news.datasource.username}")
    private String user;

    @Value("${news.datasource.password}")
    private String password;

    @Value("${news.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "newsDataSource")
    public DataSource newsDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "newsTransactionManager")
    public DataSourceTransactionManager newsTransactionManager() {
        return new DataSourceTransactionManager(newsDataSource());
    }

    @Bean(name = "newsSqlSessionFactory")
    public SqlSessionFactory newsSqlSessionFactory(@Qualifier("newsDataSource") DataSource newsDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(newsDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(NewsDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}