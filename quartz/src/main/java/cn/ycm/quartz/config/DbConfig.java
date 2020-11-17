package cn.ycm.quartz.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-13
 */
@Configuration
@MapperScan(basePackages = {"cn.ycm.quartz.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DbConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "dataSource")
    public DataSource dataSource() throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setJdbcUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setConnectionTestQuery("SELECT 1");
        return dataSource;

    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactoryBean.setConfiguration(configuration);

//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(new BaseMetaObjectHandler());
//        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
//        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}
