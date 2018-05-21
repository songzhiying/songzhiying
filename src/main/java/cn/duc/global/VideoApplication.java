package cn.duc.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * CTS Bootstrap Application
 *
 * @author Jason Wu
 * @since 1.0.0
 */
@SpringBootApplication
public class VideoApplication {

    @Configuration
    protected static class WebMvcConfigurer extends WebMvcConfigurerAdapter {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedHeaders("*")
                    .allowedMethods("*")
                    .allowedOrigins("*");
        }
    }

    @Configuration
    @EnableTransactionManagement(proxyTargetClass = true)
    @EnableJpaRepositories(basePackages = "cn.duc.global.repository.jpa")
    protected static class SpringDataConfiguration {

        @Bean
        public SpelAwareProxyProjectionFactory projectionFactory() {
            return new SpelAwareProxyProjectionFactory();
        }
    }

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
        SpringApplication.run(VideoApplication.class, args);
    }
}
