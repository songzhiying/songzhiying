package cn.duc.global;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.pagehelper.PageHelper;

/**
 * CTS Bootstrap Application
 *
 * @author Jason Wu
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("cn.duc.global.repository.dao")
public class SzyApplication {

//    @Configuration
//    protected static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**")
//                    .allowedHeaders("*")
//                    .allowedMethods("*")
//                    .allowedOrigins("*");
//        }
//    }
//
//    @Configuration
//    @EnableTransactionManagement(proxyTargetClass = true)
//    protected static class SpringDataConfiguration {
//
//       /* @Bean
//        public SpelAwareProxyProjectionFactory projectionFactory() {
//            return new SpelAwareProxyProjectionFactory();
//        }*/
//    }

//    @Configuration
//    @EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
//    protected static class WebSecurityConfiguration {
//
//        @Bean
//        public UserDetailsService userDetailsService() {
//            return new UserDetailsServiceImpl();
//        }
//
//        @Bean
//        public BCryptPasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder(12);
//        }
//    }

    public static void main(String[] args) {
        SpringApplication.run(SzyApplication.class, args);
    }
}
