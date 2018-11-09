package hh.palvelinohjelmointi.bikerental.repository;

import hh.palvelinohjelmointi.bikerental.domain.Bike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Search bikes
 */

public interface BikeRepository extends CrudRepository<Bike, Long> {
    List<Bike> findBikesByManufacturer(String manufacturer);
}
