package br.com.erickmarques;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableRabbit
@EnableJpaAuditing
public class JavaEmailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEmailSenderApplication.class, args);
	}

}
