package de.mfraas.datatracker;

//import de.mfraas.datatracker.configuration.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@Import({PersistenceContext.class})
public class S7serverApplication {

	/*
	public static void main(String[] args) {
		SpringApplication.run(S7serverApplication.class, args);
	}
	*/

	@Bean
	public ProgressBeanPostProcessor progressBeanPostProcessor() {
		return new ProgressBeanPostProcessor();
	}

	@Component
	class TestCLR implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			System.out.println("Hello World from Server");
		}
	}
}
