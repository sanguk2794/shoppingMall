/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.prod.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@ComponentScan(basePackages = "net.toyproject.mall.prod")
@EnableJpaRepositories(basePackages = "net.toyproject.mall.prod")
@EntityScan("net.toyproject.mall.prod")
public class ProdJPAConfig {

}
