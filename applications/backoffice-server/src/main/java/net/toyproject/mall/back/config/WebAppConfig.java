/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.back.config;

import net.toyproject.mall.back.config.interceptor.CheckInterceptor;
import net.toyproject.mall.back.util.ConstUtils;
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
        registry.addResourceHandler(ConstUtils.RESOURCE_PATH_JS)
                .addResourceLocations("classpath:/static/r/js/");

        registry.addResourceHandler(ConstUtils.RESOURCE_PATH_CSS)
                .addResourceLocations("classpath:/static/r/css/");

        registry.addResourceHandler(ConstUtils.RESOURCE_PATH_IMG)
                .addResourceLocations("classpath:/static/r/img/");

        registry.addResourceHandler(ConstUtils.RESOURCE_PATH_SCSS)
                .addResourceLocations("classpath:/static/r/scss/");

        registry.addResourceHandler(ConstUtils.RESOURCE_PATH_VENDOR)
                .addResourceLocations("classpath:/static/r/vendor/");
    }
}