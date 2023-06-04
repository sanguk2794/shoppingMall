/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.config;

import net.toyproject.mall.back.config.interceptor.CheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public CheckInterceptor checkInterceptor() {
        return new CheckInterceptor();
    }

    @Bean
    public MappedInterceptor interceptor() {
        return new MappedInterceptor(new String[]{"/**"}, checkInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/r/js/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/r/css/");

        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/r/img/");

        registry.addResourceHandler("/scss/**")
                .addResourceLocations("classpath:/static/r/scss/");

        registry.addResourceHandler("/vendor/**")
                .addResourceLocations("classpath:/static/r/vendor/");
    }
}