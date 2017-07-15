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
@MapperScan(basePackages = XhDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "xhSqlSessionFactory")
public class XhDataSourceConfig {

    // 精确到 xh 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.bymarx.account.dao.wordpress.xinminxuehui";
    static final String MAPPER_LOCATION = "classpath:mapper/wordpress/xinminxuehui/*.xml";

    @Value("${xh.datasource.url}")
    private String url;

    @Value("${xh.datasource.username}")
    private String user;

    @Value("${xh.datasource.password}")
    private String password;

    @Value("${xh.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "xhDataSource")
    public DataSource xhDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "xhTransactionManager")
    public DataSourceTransactionManager xhTransactionManager() {
        return new DataSourceTransactionManager(xhDataSource());
    }

    @Bean(name = "xhSqlSessionFactory")
    public SqlSessionFactory xhSqlSessionFactory(@Qualifier("xhDataSource") DataSource xhDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(xhDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(XhDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}