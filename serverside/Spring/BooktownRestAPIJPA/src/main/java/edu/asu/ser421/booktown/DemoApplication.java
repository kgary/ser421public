package edu.asu.ser421.booktown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = {"edu.asu.ser421.booktown.model","edu.asu.ser421.booktown.repository"})
//@SpringBootApplication(scanBasePackages = {"edu.asu.ser421.booktown"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
