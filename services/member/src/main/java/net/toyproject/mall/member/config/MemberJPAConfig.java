/**
 * @author sanguk on 2023/06/02
 */

package net.toyproject.mall.member.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@ComponentScan(basePackages = "net.toyproject.mall.member")
@EnableJpaRepositories(basePackages = "net.toyproject.mall.member")
@EntityScan("net.toyproject.mall.member")
public class MemberJPAConfig {

}
