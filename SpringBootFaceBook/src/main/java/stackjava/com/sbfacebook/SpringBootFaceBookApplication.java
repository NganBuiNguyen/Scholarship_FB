package stackjava.com.sbfacebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages="stackjava.com.sbfacebook.db")
public class SpringBootFaceBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFaceBookApplication.class, args);
	}
	
}
