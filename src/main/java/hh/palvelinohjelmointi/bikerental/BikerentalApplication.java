package hh.palvelinohjelmointi.bikerental;

import hh.palvelinohjelmointi.bikerental.domain.*;
import hh.palvelinohjelmointi.bikerental.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class BikerentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikerentalApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertData(BikeRepository bikeRepository,
										UserRepository userRepository,
										RentalRepository rentalRepository,
										CategoryRepository categoryRepository,
										RentalStatusRepository rentalStatusRepository) {

		return (args) -> {

			/**
			 * Bike category: city, hybrid, mountain, junior
			 */

			Category categoryCity = new Category("city");
			Category categoryHybrid = new Category("hybrid");
			Category categoryMountain = new Category("mountain");
			Category categoryJunior = new Category("junior");



			categoryRepository.save(categoryCity);
			categoryRepository.save(categoryHybrid);
			categoryRepository.save(categoryMountain);
			categoryRepository.save(categoryJunior);


			/**
			 * Rental status: active, paid, cancelled
			 */

			RentalStatus statusActive = new RentalStatus("active");
			RentalStatus statusPaid = new RentalStatus("paid");
			RentalStatus statusCancelled = new RentalStatus("cancelled");

			rentalStatusRepository.save(statusActive);
			rentalStatusRepository.save(statusPaid);
			rentalStatusRepository.save(statusCancelled);

			/**
			 * Some sample bikes
			 */

			Bike bikeHelkama = new Bike("Helkama", "Jopo", 9.99, 2, categoryCity);
			Bike bikeTunturi = new Bike("Tunturi", "RX300", 9.99, 2, categoryHybrid);
			Bike bikeNishiki = new Bike("Nishiki", "Bombardier", 9.99, 2, categoryMountain);
			Bike bikeJupiter = new Bike("Jupiter", "Raptor", 9.99, 2, categoryJunior);


			bikeRepository.save(bikeHelkama);
			bikeRepository.save(bikeTunturi);
			bikeRepository.save(bikeNishiki);
			bikeRepository.save(bikeJupiter);

			/**
			 * Administrator
			 */


			User userAdmin = new User("isoveli", "$2a$04$0d8VUMnL1lKpg6kio2aE1ulCBfSSaCTYYV7nSYJ77BOH8GYnIyp1K", "ADMIN");

			userRepository.save(userAdmin);
			/*
			userRepository.save(user1);
			*/
			/**
			 * Date format for rental start date and end date: "dd.MM.yyyy"
			 */

			SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");

			/**
			 * rental examples
			 */



			Rental rental1 = new Rental("Eemeli",
					"Virtanen",
					"eemeli@mail.com",
					"0401234567",
					fdate.parse("01.11.2018"),
					fdate.parse("02.11.2018"),
					bikeHelkama,
					statusActive);

			Rental rental2 = new Rental("Kalle",
					"Kolkki",
					"kolkki@mail.com",
					"0400123",
					fdate.parse("04.06.2019"),
					fdate.parse("16.16.2019"),
					bikeJupiter,
					statusPaid);

			rentalRepository.save(rental1);
			rentalRepository.save(rental2);




/*
			SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			sittingRepository.save(new Sitting(fdate.parse("10.12.2018 17:00")));
			sittingRepository.save(new Sitting(fdate.parse("10.12.2018 20:00")));
			sittingRepository.save(new Sitting(fdate.parse("11.12.2018 17:00")));
			sittingRepository.save(new Sitting(fdate.parse("11.12.2018 20:00")));
			sittingRepository.save(new Sitting(fdate.parse("12.12.2018 17:00")));
			sittingRepository.save(new Sitting(fdate.parse("12.12.2018 20:00")));

			// Set<Table> tables = sittingRepository.findAllByDate(fdate.parse("10.12.2018 20:00"));

*/

		};
	}






}
