package spring.intro.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spring.intro.model.User;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
        "spring.intro.service",
        "spring.intro.dao"
})

public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(env.getProperty("db.driver"));
        basicDataSource.setUrl(env.getProperty("db.url"));
        basicDataSource.setUsername(env.getProperty("db.username"));
        basicDataSource.setPassword(env.getProperty("db.password"));
        return basicDataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setAnnotatedClasses(User.class);
        return sessionFactoryBean;
    }
}
