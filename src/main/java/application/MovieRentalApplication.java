package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MovieRentalApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieRentalApplication.class, args);
	}
}
