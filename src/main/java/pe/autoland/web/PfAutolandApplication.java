package pe.autoland.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"pe.autoland.thymeleaf.controller","pe.autoland.web.service"})
@PropertySource("classpath:application.properties")
@EntityScan("pe.autoland.web.model")
//@EnableJpaRepositories("pe.utoland.web.repository")

public class PfAutolandApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfAutolandApplication.class, args);
	}

}