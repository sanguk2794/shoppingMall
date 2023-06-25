/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.co.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@ComponentScan(basePackages = "net.toyproject.mall.co")
@EnableJpaRepositories(basePackages = "net.toyproject.mall.co")
@EntityScan("net.toyproject.mall.co")
public class CoJPAConfig {

}
