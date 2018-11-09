package hh.palvelinohjelmointi.bikerental.repository;

import hh.palvelinohjelmointi.bikerental.domain.RentalStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Rental status: active, paid, cancelled
 * Rentals are not deleted from the database, they are moved to cancelled status if the rental is no longer active
 */


public interface RentalStatusRepository extends CrudRepository<RentalStatus, Long> {

    Optional<RentalStatus> findRentalStatusByStatusName(String statusName);

}
