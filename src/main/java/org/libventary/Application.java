package org.libventary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages = "org.libventary",  excludeFilters = @Filter({ Configuration.class }))
@Import({
    AxonConfig.class,
    PersistenceConfig.class
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
