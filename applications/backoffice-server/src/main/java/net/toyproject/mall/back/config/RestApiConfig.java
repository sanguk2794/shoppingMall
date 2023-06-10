
package net.toyproject.mall.back.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import net.toyproject.mall.restapi.client.ApiClientTarget;
import net.toyproject.mall.restapi.client.member.MemberApi;
import net.toyproject.mall.restapi.client.member.LoginApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestApiConfig {

    @Value("${restapi.base-url}")
    private String apiBaseUrl;

    @Value("${restapi.type}")
    private String apiType;

    @Value("${restapi.log-level}")
    private Logger.Level apiLogLevel;

    private <T> T createApi(Class<T> apiClass) {
        ApiClientTarget<T> target = new ApiClientTarget<T>(apiClass, apiBaseUrl);
        OkHttpClient client = new OkHttpClient();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper()))
                .decoder(new JacksonDecoder(objectMapper()))
                .logger(new Logger.ErrorLogger()).logLevel(apiLogLevel)
                .client(client).retryer(Retryer.NEVER_RETRY)
                .target(target);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .disable(SerializationFeature.INDENT_OUTPUT)
                .setDateFormat(new StdDateFormat());
    }

    @Bean
    public MemberApi memberApi() {
        return createApi(MemberApi.class);
    }

    @Bean
    public LoginApi loginApi() {
        return createApi(LoginApi.class);
    }
}
