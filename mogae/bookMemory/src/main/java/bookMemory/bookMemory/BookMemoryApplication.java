package bookMemory.bookMemory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BookMemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMemoryApplication.class, args);
	}

}
