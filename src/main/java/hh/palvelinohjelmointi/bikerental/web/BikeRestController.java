package hh.palvelinohjelmointi.bikerental.web;

import hh.palvelinohjelmointi.bikerental.domain.Bike;
import hh.palvelinohjelmointi.bikerental.domain.Rental;
import hh.palvelinohjelmointi.bikerental.repository.BikeRepository;
import hh.palvelinohjelmointi.bikerental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
public class BikeRestController {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    /**
     * Bike list REST
     * @return
     */
    @GetMapping(value="/bikes")
    public Iterable<Bike> showBikesRest() {
        return bikeRepository.findAll();
    }

    /**
     * Save new bike REST
     * @return
     */
    @PostMapping(value = "/bikes")
    public Bike createBike(@RequestBody Bike bike) {

        Bike savedBike =
        bikeRepository.save(bike);

        return savedBike;
    }

    /**
     * Save new rental agreement
     * @param rental
     * @return
     */
    @PostMapping("/rentals")
    public Rental createRental(@RequestBody Rental rental) {



        Rental savedRental = rentalRepository.save(rental);

        return savedRental;
    }

    /**
     * Rental list REST
     * @return
     */
    @GetMapping("/rentals")
    public Iterable<Rental> showRentalsRest() {
        return rentalRepository.findAll();
    }


}
