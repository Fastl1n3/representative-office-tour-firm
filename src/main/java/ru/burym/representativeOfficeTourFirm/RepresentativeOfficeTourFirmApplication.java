package ru.burym.representativeOfficeTourFirm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RepresentativeOfficeTourFirmApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepresentativeOfficeTourFirmApplication.class, args);
	}

}
