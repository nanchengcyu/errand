package cn.nanchengyu.errand.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("*") // 允许跨域访问的源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许请求方法
                .allowedHeaders("*") // 允许请求头
                .allowCredentials(true) // 是否发送cookie
                .maxAge(3600); // 预检请求的有效期，单位秒
    }
}
