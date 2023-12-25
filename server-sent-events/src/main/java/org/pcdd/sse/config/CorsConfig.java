package org.pcdd.sse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author pcdd
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 允许访问的源，可以设置为具体的域名或 "*" 表示允许任何域名访问
        config.addAllowedOrigin("*");

        // 允许的请求方法，如：GET、POST、PUT、DELETE等
        config.addAllowedMethod("*");

        // 允许的头信息，如：Content-Type、Authorization等
        config.addAllowedHeader("*");

        // 是否支持凭证（例如，是否发送 Cookie）
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}