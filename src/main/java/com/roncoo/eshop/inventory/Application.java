package com.roncoo.eshop.inventory;

import com.roncoo.eshop.inventory.listener.InitListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Component: 库存服务的Application入口类
 * Description:
 * Date: 17/6/30
 *
 * @author yue.zhang
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.roncoo.eshop.inventory.mapper")
public class Application  {

    /**
     * 构建数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DataSource();
    }

    /**
     * 构建mybatis的入口类，SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 构建事务管理器
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JedisCluster JedisClusterFactory(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();

        jedisClusterNodes.add(new HostAndPort("192.168.31.235",7001));
        jedisClusterNodes.add(new HostAndPort("192.168.31.184",7003));
        jedisClusterNodes.add(new HostAndPort("192.168.31.180",7005));
        jedisClusterNodes.add(new HostAndPort("192.168.31.180",7006));

        return new JedisCluster(jedisClusterNodes);

    }

    /**
     * 注册监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new InitListener());
        return bean;
    }

    /**
     * Spring Boot 启动类
     * @param args
     */
    public static void main(String [] args){
        SpringApplication.run(Application.class,args);
    }

}
