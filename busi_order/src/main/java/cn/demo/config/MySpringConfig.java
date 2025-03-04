package cn.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: gina
 * @Date: 2024-12-22
 * @Description:
 */

@Configuration
public class MySpringConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}