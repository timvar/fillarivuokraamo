package hh.palvelinohjelmointi.bikerental.repository;

import hh.palvelinohjelmointi.bikerental.domain.Bike;
import hh.palvelinohjelmointi.bikerental.domain.Rental;
import hh.palvelinohjelmointi.bikerental.domain.RentalStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;



public interface RentalRepository extends CrudRepository<Rental, Long> {


    /**
     * checks the number of rentals on a given period to avoid overbooking
     */
    Iterable<Rental> findRentalsByStartDayIsLessThanEqualAndEndDayIsGreaterThanEqualAndBike(Date endDay, Date startDay, Bike bike);
    long countRentalsByStartDayIsLessThanEqualAndEndDayIsGreaterThanEqualAndBikeAndStatusIs(Date endDay, Date startDay, Bike bike, RentalStatus status);

    /**
     * Rentals by bike
     * @param bike
     * @return
     */

    Iterable<Rental> findRentalsByBike(Bike bike);

}
