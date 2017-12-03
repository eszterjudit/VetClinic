package com.me.vetclinic.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.me.vetclinic.domain"})
@EnableJpaRepositories(basePackages = {"com.me.vetclinic.repository"})
@EnableTransactionManagement
@Profile("test")
public class RepositoryConfiguration {

}
